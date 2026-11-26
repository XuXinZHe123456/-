package com.xuxinzhe.Project.Function;

import com.xuxinzhe.Project.Class.CardState;
import com.xuxinzhe.Project.Class.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class File
{
    //数据文件，保存在程序运行目录下
    private static final String DATA_PATH = "studentData.txt";

    //启动时调用：先读数据，再注册退出时自动保存
    public static void init()
    {
        loadData();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveData()));
    }

    //读取数据：把文件里的学生读进 User.studentArrayList
    public static void loadData()
    {
        java.io.File dataFile = new java.io.File(DATA_PATH);
        if(!dataFile.exists())
        {
            //第一次运行还没有数据文件，直接返回
            return;
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(dataFile), StandardCharsets.UTF_8)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                if(line.isBlank())
                {
                    continue;
                }
                String[] parts = line.split("\\|", -1);
                if(parts.length < 8)
                {
                    continue;
                }
                try
                {
                    Student s = new Student(parts[0], parts[1], parts[2],
                            new BigDecimal(parts[3]), parts[4], parts[5],
                            CardState.valueOf(parts[6]), Integer.parseInt(parts[7]));
                    User.studentArrayList.add(s);
                }
                catch(RuntimeException e)
                {
                    //某一行格式不对就跳过这一行
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("读取数据失败：" + e.getMessage());
        }
    }

    //保存数据：把 User.studentArrayList 写进文件，成功返回 true
    public static boolean saveData()
    {
        try(BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(DATA_PATH), StandardCharsets.UTF_8)))
        {
            for(Student s : User.studentArrayList)
            {
                bw.write(s.getSchoolName() + "|" + s.getStuName() + "|" + s.getStuID()
                        + "|" + s.getInitialAmount() + "|" + s.getStuGrade()
                        + "|" + s.getStuClass() + "|" + s.getCardStatus()
                        + "|" + s.getStuNum());
                bw.newLine();
            }
            return true;
        }
        catch(Exception e)
        {
            System.out.println("保存数据失败：" + e.getMessage());
            return false;
        }
    }
}
