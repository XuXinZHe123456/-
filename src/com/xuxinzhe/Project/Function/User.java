package com.xuxinzhe.Project.Function;
import com.xuxinzhe.Project.Class.CardState;
import com.xuxinzhe.Project.Class.Student;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class User
{
    static ArrayList<Student> studentArrayList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static Student stu =  new Student();

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
        studentArrayList.add(new Student("阳光学院",
                 name, id,
                new BigDecimal("20.00"),
                grade, classNum, cardStatus));
        System.out.println("添加成功");
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
    //修改学生学院
    public static void updateStu()
    {

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
            System.out.println("卡余额：" + s.getInitialAmount());
            System.out.println("卡状态：" + c.getDescription());
            System.out.println();
        }
    }

}
