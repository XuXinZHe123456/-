package com.xuxinzhe.Project.Function;

import com.xuxinzhe.Project.Class.CardState;
import com.xuxinzhe.Project.Class.Student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database
{
    //数据库文件：程序运行目录下的 StudentCard.accdb（Access 2007+ 格式）
    private static final String DB_FILE = Paths.get("StudentCard.accdb").toAbsolutePath().toString();
    //UCanAccess 的驱动类（注意 canaccess 是小写 c）
    private static final String DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";

    //UCanAccess 的 JDBC 连接地址；数据库文件不存在时让它新建一个 2010 格式的 .accdb
    private static String getUrl()
    {
        String url = "jdbc:ucanaccess://" + DB_FILE.replace("\\", "/");
        if(!Files.exists(Paths.get(DB_FILE)))
        {
            url = url + ";newDatabaseVersion=V2010";
        }
        return url;
    }

    //Students 表建表语句
    private static final String CREATE_TABLE =
            "CREATE TABLE Students ("
            + "SchoolName TEXT(50), "
            + "StuName TEXT(50), "
            + "StuID TEXT(50), "
            + "Amount DOUBLE, "
            + "StuGrade TEXT(50), "
            + "StuClass TEXT(50), "
            + "CardState TEXT(20), "
            + "ClassCount LONG)";

    //启动时调用：连库、建表、读数据，并注册退出时双写（Access + 文本）
    public static void init()
    {
        if(!isDriverAvailable())
        {
            System.out.println("提示：未找到 UCanAccess 驱动，Access 功能不可用，本次只使用文本存档（studentData.txt）");
            File.init();
            return;
        }
        if(!ensureTable())
        {
            System.out.println("提示：Access 数据库不可用，本次只使用文本存档（studentData.txt）");
            File.init();
            return;
        }
        loadData();
        if(User.studentArrayList.size() == 0)
        {
            //Access 里没有学生，把文本存档里的数据迁移过来
            File.loadData();
            if(User.studentArrayList.size() > 0)
            {
                saveData();
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
        {
            saveData();
            File.saveData();
        }));
    }

    //判断项目里有没有加 UCanAccess 驱动
    public static boolean isDriverAvailable()
    {
        try
        {
            Class.forName(DRIVER);
            return true;
        }
        catch(ClassNotFoundException e)
        {
            return false;
        }
    }

    //确保 Students 表存在，没有就建一张
    private static boolean ensureTable()
    {
        try(Connection conn = DriverManager.getConnection(getUrl()))
        {
            if(tableExists(conn))
            {
                return true;
            }
            try(Statement st = conn.createStatement())
            {
                st.executeUpdate(CREATE_TABLE);
            }
            return tableExists(conn);
        }
        catch(Exception e)
        {
            System.out.println("连接 Access 失败：" + e.getMessage());
            return false;
        }
    }

    //表是否存在：能查就说明存在
    private static boolean tableExists(Connection conn)
    {
        try(Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Students"))
        {
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }

    //从 Access 读取所有学生到 User.studentArrayList
    public static void loadData()
    {
        try(Connection conn = DriverManager.getConnection(getUrl());
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT SchoolName, StuName, StuID, Amount, StuGrade, StuClass, CardState, ClassCount FROM Students"))
        {
            while(rs.next())
            {
                User.studentArrayList.add(new Student(
                        rs.getString("SchoolName"),
                        rs.getString("StuName"),
                        rs.getString("StuID"),
                        BigDecimal.valueOf(rs.getDouble("Amount")).setScale(2, RoundingMode.HALF_UP),
                        rs.getString("StuGrade"),
                        rs.getString("StuClass"),
                        CardState.valueOf(rs.getString("CardState")),
                        rs.getInt("ClassCount")));
            }
        }
        catch(Exception e)
        {
            System.out.println("Access 读取失败：" + e.getMessage());
        }
    }

    //把 User.studentArrayList 全部写进 Access（先清空表再插入）
    public static boolean saveData()
    {
        if(!isDriverAvailable())
        {
            System.out.println("提示：未安装 UCanAccess 驱动，跳过 Access 保存");
            return false;
        }
        try(Connection conn = DriverManager.getConnection(getUrl());
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Students (SchoolName, StuName, StuID, Amount, StuGrade, StuClass, CardState, ClassCount) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"))
        {
            st.executeUpdate("DELETE FROM Students");
            for(Student s : User.studentArrayList)
            {
                ps.setString(1, s.getSchoolName());
                ps.setString(2, s.getStuName());
                ps.setString(3, s.getStuID());
                ps.setDouble(4, s.getInitialAmount().doubleValue());
                ps.setString(5, s.getStuGrade());
                ps.setString(6, s.getStuClass());
                ps.setString(7, s.getCardStatus());
                ps.setInt(8, s.getStuNum());
                ps.executeUpdate();
            }
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Access 保存失败：" + e.getMessage());
            return false;
        }
    }
}
