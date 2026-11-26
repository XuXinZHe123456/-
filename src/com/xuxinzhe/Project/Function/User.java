package com.xuxinzhe.Project.Function;
import com.xuxinzhe.Project.Class.CardState;
import com.xuxinzhe.Project.Class.Student;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    static ArrayList<Student> studentArrayList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Student stu =  new Student();
    //卡操作记录
    static ArrayList<String> cardLog = new ArrayList<>();

    //统计某个年级、某个班级的学生人数
    private static int countClass(String grade, String classNum)
    {
        int count = 0;
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum))
            {
                count++;
            }
        }
        return count;
    }

    //把学号转成数字，学号不是纯数字时返回 null
    private static Long toStuNumber(String stuID)
    {
        try
        {
            return Long.parseLong(stuID.trim());
        }
        catch(NumberFormatException e)
        {
            return null;
        }
    }

    //把某个班级所有学生记录里的班级人数统一更新为最新人数
    private static void syncClassNum(String grade, String classNum, int stuNum)
    {
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum))
            {
                s.setStuNum(stuNum);
            }
        }
    }

    //增加学生信息
    public static void addStudent()
    {
        System.out.println("学生名字：");
        String name = sc.nextLine();
        while(name.isBlank())
        {
            System.out.println("学生名字不能为空，请重新输入：");
            name = sc.nextLine();
        }
        System.out.println("学生ID：");
        String id = sc.nextLine();
        while(id.isBlank())
        {
            System.out.println("学生ID不能为空，请重新输入：");
            id = sc.nextLine();
        }
        System.out.println("学生年级：");
        String grade = sc.nextLine();
        while(grade.isBlank())
        {
            System.out.println("学生年级不能为空，请重新输入：");
            grade = sc.nextLine();
        }
        System.out.println("学生班级：");
        String classNum = sc.nextLine();
        while(classNum.isBlank())
        {
            System.out.println("学生班级不能为空，请重新输入：");
            classNum = sc.nextLine();
        }
        CardState cardStatus = CardState.Normal;
        //同一个年级、同一个班级，人数在原有基础上 +1
        int stuNum = countClass(grade, classNum) + 1;
        studentArrayList.add(new Student("阳光学院",
                 name, id,
                new BigDecimal("20.00"),
                grade, classNum, cardStatus, stuNum));
        //同步该班级已有学生记录里的人数，保证显示一致
        syncClassNum(grade, classNum, stuNum);
        System.out.println("添加成功，当前班级共有" + stuNum + "人");
    }

    //修改学生名字
    public static void updateStuName()
    {
        System.out.println("输入学生名字：");
        String oldName = sc.nextLine();
        while(oldName.isBlank())
        {
            System.out.println("学生名不能为空");
            oldName = sc.nextLine();
        }
        System.out.println("输入学生名字：");
        String oldKey = sc.nextLine();
        while(oldKey.isBlank())
        {
            System.out.println("学号不能为空");
            oldKey = sc.nextLine();
        }
        boolean idKey = Verify.verifyStuID(oldKey);
        if(idKey)
        {
            System.out.println("输入新学生的名字：");
            String newName = sc.nextLine();
            while(newName.isBlank())
            {
                System.out.println("新名字不得为空");
                newName = sc.nextLine();
            }
            while(newName.equals(oldName))
            {
                System.out.println("新名字不得与旧名字相同");
                newName = sc.nextLine();
                while(newName.isBlank())
                {
                    System.out.println("新名字不得为空");
                    newName = sc.nextLine();
                }
            }
            for(Student s : studentArrayList)
            {
                if(s.getStuName().equals(oldName))
                {
                    if(!canOperate(s))
                    {
                        System.out.println("修改失败");
                        return;
                    }
                    s.setStuName(newName);
                    System.out.println("修改成功");
                    break;
                }
            }
        }
        else
        {
            System.out.println("修改失败");
        }
    }

    public static void  deleteStu()
    {
        if(studentArrayList.isEmpty())
        {
            System.out.println("暂无可修改");
            return;
        }
        

    }

    //显示班级人数
    public static void showClassNum()
    {
        if(studentArrayList.size() == 0)
        {
            System.out.println("暂无学生信息，无法显示班级人数");
            return;
        }
        System.out.println("输入年级：");
        String grade = sc.nextLine();
        while(grade.isBlank())
        {
            System.out.println("该空不得为空");
            grade = sc.nextLine();
        }
        System.out.println("输入班级：");
        String classNum = sc.nextLine();
        while (classNum.isBlank())
        {
            System.out.println("该空不得为空");
            classNum = sc.nextLine();
        }
        //人数以同一个年级、同一个班级的学生数量为准
        int count = countClass(grade, classNum);
        if(count == 0)
        {
            System.out.println(grade + "年级" + classNum + "班暂无学生");
        }
        else
        {
            System.out.println(grade + "年级" + classNum + "班的人数是：" + count);
        }
    }

    //删除学生
    public static void deleteStudent()
    {
        if(studentArrayList.size() == 0)
        {
            System.out.println("暂无学生信息，无法删除");
            return;
        }
        System.out.println("输入学生所属年级：");
        String grade = sc.nextLine();
        while(grade.isBlank())
        {
            System.out.println("该空不得为空");
            grade = sc.nextLine();
        }
        System.out.println("输入学生所属班级：");
        String classNum = sc.nextLine();
        while(classNum.isBlank())
        {
            System.out.println("该空不得为空");
            classNum = sc.nextLine();
        }
        //先把该年级、该班级的学生找出来
        ArrayList<Student> classList = new ArrayList<>();
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum))
            {
                classList.add(s);
            }
        }
        if(classList.size() == 0)
        {
            System.out.println(grade + "年级" + classNum + "班暂无学生");
            return;
        }
        System.out.println("输入要删除学生的学号：");
        String stuID = sc.nextLine();
        while(stuID.isBlank())
        {
            System.out.println("学号不能为空");
            stuID = sc.nextLine();
        }
        Student target = null;
        for(Student s : classList)
        {
            if(s.getStuID().equals(stuID))
            {
                target = s;
                break;
            }
        }
        if(target == null)
        {
            System.out.println("该班级没有学号为" + stuID + "的学生，删除失败");
            return;
        }
        //冻结的卡不能删除，需要先解冻
        if(!canOperate(target))
        {
            System.out.println("删除失败");
            return;
        }
        //删除该学生
        studentArrayList.remove(target);
        classList.remove(target);
        System.out.println("删除成功");
        //删除后，学号大于被删除学号的学生学号 -1，小于的保持不变
        Long deletedNum = toStuNumber(stuID);
        if(deletedNum == null)
        {
            System.out.println("该学号不是数字，未做学号前移");
        }
        else
        {
            for(Student s : classList)
            {
                Long num = toStuNumber(s.getStuID());
                if(num != null && num > deletedNum)
                {
                    s.setStuID(String.valueOf(num - 1));
                }
            }
            System.out.println("该班级学号大于" + stuID + "的学生学号已全部-1");
        }
        //该班级人数减1，并同步到班级里剩下的学生
        int remainCount = classList.size();
        syncClassNum(grade, classNum, remainCount);
        System.out.println(grade + "年级" + classNum + "班剩余" + remainCount + "人");
    }

    //展示学生信息
    public static void showStu()
    {
        CardState c = CardState.Normal;
        if(studentArrayList.size()==0)
        {
            System.out.println("暂无学生可显示");
            return;
        }
        for(Student s:studentArrayList)
        {
            System.out.println("=====学生信息=====");
            System.out.println("学校：" + s.getSchoolName());
            System.out.println("姓名：" + s.getStuName());
            System.out.println("学号：" + s.getStuID());
            System.out.println("年级：" + s.getStuGrade());
            System.out.println("班级：" + s.getStuClass());
            System.out.println("班级人数：" + s.getStuNum());
            System.out.println("卡余额：" + s.getInitialAmount());
            System.out.println("卡状态：" + cardStateText(s));
            System.out.println();
        }
    }

    //把卡状态代码翻译成中文描述
    private static String cardStateText(Student s)
    {
        for(CardState c : CardState.values())
        {
            if(c.name().equals(s.getCardStatus()))
            {
                return c.getDescription();
            }
        }
        return s.getCardStatus();
    }

    //查找某个年级、某个班级里指定学号的学生，找不到返回 null
    private static Student findStudent(String grade, String classNum, String stuID)
    {
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum)
                    && s.getStuID().equals(stuID))
            {
                return s;
            }
        }
        return null;
    }

    //冻结的卡管理员不能操作（解冻除外）
    private static boolean canOperate(Student s)
    {
        if(CardState.Freeze.name().equals(s.getCardStatus()))
        {
            System.out.println("该卡已冻结，管理员无法操作，请先解冻");
            return false;
        }
        return true;
    }

    //读入一行非空文本（自动去掉首尾空格）
    private static String readText(String tip)
    {
        System.out.println(tip);
        String text = sc.nextLine().trim();
        while(text.isBlank())
        {
            System.out.println("该空不得为空，请重新输入：");
            text = sc.nextLine().trim();
        }
        return text;
    }

    //输入年级、班级、学号，找到要操作的学生
    private static Student readStudent()
    {
        String grade = readText("输入学生所属年级：");
        String classNum = readText("输入学生所属班级：");
        String stuID = readText("输入学生学号：");
        Student s = findStudent(grade, classNum, stuID);
        if(s == null)
        {
            System.out.println(grade + "年级" + classNum + "班没有学号为" + stuID + "的学生");
        }
        return s;
    }

    //读入一个大于0的金额
    private static BigDecimal readAmount(String tip)
    {
        while(true)
        {
            System.out.println(tip);
            String line = sc.nextLine();
            try
            {
                BigDecimal amount = new BigDecimal(line.trim());
                if(amount.compareTo(BigDecimal.ZERO) > 0)
                {
                    return amount;
                }
            }
            catch(NumberFormatException e)
            {
                //输入不是数字，统一走下面的提示
            }
            System.out.println("金额格式不正确，请输入大于0的数字：");
        }
    }

    //管理员：冻结卡
    public static void freezeCard()
    {
        Student s = readStudent();
        if(s == null || !canOperate(s))
        {
            return;
        }
        s.setCardStatus(CardState.Freeze);
        addLog("冻结卡：" + s.getStuGrade() + "年级" + s.getStuClass() + "班 学号"
                + s.getStuID() + " " + s.getStuName());
        System.out.println("冻结成功，现在无法对该卡进行任何操作");
    }

    //管理员：解冻卡（只能解冻）
    public static void unfreezeCard()
    {
        Student s = readStudent();
        if(s == null)
        {
            return;
        }
        if(CardState.Freeze.name().equals(s.getCardStatus()))
        {
            s.setCardStatus(CardState.Normal);
            addLog("解冻卡：" + s.getStuGrade() + "年级" + s.getStuClass() + "班 学号"
                    + s.getStuID() + " " + s.getStuName());
            System.out.println("解冻成功，现在可以正常操作该卡");
        }
        else
        {
            System.out.println("该卡未冻结，无需解冻");
        }
    }

    //管理员：充值
    public static void recharge()
    {
        Student s = readStudent();
        if(s == null || !canOperate(s))
        {
            return;
        }
        BigDecimal amount = readAmount("输入充值金额：");
        s.setInitialAmount(s.getInitialAmount().add(amount));
        addLog("充值：" + s.getStuGrade() + "年级" + s.getStuClass() + "班 学号"
                + s.getStuID() + " " + s.getStuName() + " 金额" + amount
                + " 余额" + s.getInitialAmount());
        System.out.println("充值成功，当前余额：" + s.getInitialAmount());
    }

    //管理员：消费
    public static void consume()
    {
        Student s = readStudent();
        if(s == null || !canOperate(s))
        {
            return;
        }
        BigDecimal amount = readAmount("输入消费金额：");
        if(s.getInitialAmount().compareTo(amount) < 0)
        {
            System.out.println("余额不足，当前余额：" + s.getInitialAmount());
            return;
        }
        s.setInitialAmount(s.getInitialAmount().subtract(amount));
        addLog("消费：" + s.getStuGrade() + "年级" + s.getStuClass() + "班 学号"
                + s.getStuID() + " " + s.getStuName() + " 金额" + amount
                + " 余额" + s.getInitialAmount());
        System.out.println("消费成功，当前余额：" + s.getInitialAmount());
    }

    //打印一个学生的详细信息
    private static void printStudent(Student s)
    {
        System.out.println("=====学生信息=====");
        System.out.println("学校：" + s.getSchoolName());
        System.out.println("姓名：" + s.getStuName());
        System.out.println("学号：" + s.getStuID());
        System.out.println("年级：" + s.getStuGrade());
        System.out.println("班级：" + s.getStuClass());
        System.out.println("班级人数：" + s.getStuNum());
        System.out.println("卡余额：" + s.getInitialAmount());
        System.out.println("卡状态：" + cardStateText(s));
        System.out.println();
    }

    //查询学生：按学号或姓名查询某个班级里的学生
    public static void queryStudent()
    {
        if(studentArrayList.size() == 0)
        {
            System.out.println("暂无学生信息，无法查询");
            return;
        }
        String grade = readText("输入学生所属年级：");
        String classNum = readText("输入学生所属班级：");
        String key = readText("输入学号或姓名：");
        ArrayList<Student> result = new ArrayList<>();
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum)
                    && (s.getStuID().equals(key) || s.getStuName().equals(key)))
            {
                result.add(s);
            }
        }
        if(result.size() == 0)
        {
            System.out.println("没有找到符合条件的学生");
            return;
        }
        for(Student s : result)
        {
            printStudent(s);
        }
    }

    //按班级列出学生名单（按学号从小到大排序）
    public static void showClassList()
    {
        String grade = readText("输入年级：");
        String classNum = readText("输入班级：");
        ArrayList<Student> classList = new ArrayList<>();
        for(Student s : studentArrayList)
        {
            if(s.getStuGrade().equals(grade) && s.getStuClass().equals(classNum))
            {
                classList.add(s);
            }
        }
        if(classList.size() == 0)
        {
            System.out.println(grade + "年级" + classNum + "班暂无学生");
            return;
        }
        classList.sort((a, b) ->
        {
            Long na = toStuNumber(a.getStuID());
            Long nb = toStuNumber(b.getStuID());
            if(na != null && nb != null)
            {
                return na.compareTo(nb);
            }
            return a.getStuID().compareTo(b.getStuID());
        });
        System.out.println("=====" + grade + "年级" + classNum + "班学生名单=====");
        for(Student s : classList)
        {
            System.out.println("学号：" + s.getStuID() + "  姓名：" + s.getStuName()
                    + "  卡状态：" + cardStateText(s));
        }
        System.out.println("共" + classList.size() + "人");
    }

    //修改学生的年级、班级、学号（直接回车表示不修改）
    public static void updateStuInfo()
    {
        Student s = readStudent();
        if(s == null || !canOperate(s))
        {
            return;
        }
        System.out.println("输入新的年级（直接回车不修改）：");
        String newGrade = sc.nextLine().trim();
        if(newGrade.isBlank())
        {
            newGrade = s.getStuGrade();
        }
        System.out.println("输入新的班级（直接回车不修改）：");
        String newClass = sc.nextLine().trim();
        if(newClass.isBlank())
        {
            newClass = s.getStuClass();
        }
        System.out.println("输入新的学号（直接回车不修改）：");
        String newID = sc.nextLine().trim();
        if(newID.isBlank())
        {
            newID = s.getStuID();
        }
        //检查新学号在目标班级里是否已经被占用
        for(Student other : studentArrayList)
        {
            if(other != s && other.getStuGrade().equals(newGrade)
                    && other.getStuClass().equals(newClass)
                    && other.getStuID().equals(newID))
            {
                System.out.println("该班级已经存在学号为" + newID + "的学生，修改失败");
                return;
            }
        }
        String oldGrade = s.getStuGrade();
        String oldClass = s.getStuClass();
        s.setStuGrade(newGrade);
        s.setStuClass(newClass);
        s.setStuID(newID);
        //换班后同步原班级和新班级的人数
        if(!oldGrade.equals(newGrade) || !oldClass.equals(newClass))
        {
            syncClassNum(oldGrade, oldClass, countClass(oldGrade, oldClass));
            syncClassNum(newGrade, newClass, countClass(newGrade, newClass));
        }
        System.out.println("修改成功");
    }

    //记录一条卡操作日志
    private static void addLog(String action)
    {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        cardLog.add(time + "  " + action);
    }

    //查看操作记录
    public static void showLog()
    {
        if(cardLog.size() == 0)
        {
            System.out.println("暂无操作记录");
            return;
        }
        for(String log : cardLog)
        {
            System.out.println(log);
        }
    }

}
