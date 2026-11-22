package com.xuxinzhe.Project.Function;

import static com.xuxinzhe.Project.Function.User.studentArrayList;
import com.xuxinzhe.Project.Class.Student;

public class Verify
{
    //判断用户名
    public static boolean verifyName(String oldName)
    {
        for(Student s: studentArrayList)
        {
            if(oldName.equals(s.getStuName()))
            {
                return true;
            }
        }
        return false;
    }

    //判断学号
    public static boolean verifyStuID(String oldPassword)
    {
        for(Student s: studentArrayList)
        {
            if(oldPassword.equals(s.getStuID()))
            {
                return true;
            }
        }
        return false;
    }
}
