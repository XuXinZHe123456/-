package com.xuxinzhe.Project.Function;
import com.xuxinzhe.Project.Class.Student;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.xuxinzhe.Project.Function.User.stu;
import static com.xuxinzhe.Project.Function.User.studentArrayList;
public class CardMoney
{
    static Scanner sc = new Scanner(System.in);
    //校园卡充值
    public static void addMoney()
    {
        System.out.println("输入学号：");
        String stuID = sc.nextLine();
        while(stuID.isBlank())
        {
            System.out.println("学号不得为空");
            stuID = sc.nextLine();
        }
        System.out.println("输入要增加的金额：");
        String balance = sc.nextLine();
        while(balance.isBlank())
        {
            System.out.println("该空不得为空");
            balance = sc.nextLine();
        }
        BigDecimal addBalance = new BigDecimal(balance);
        if(addBalance.compareTo(BigDecimal.ZERO) <= 0)
        {
            System.out.println("充值金额必须大于0");
            return;
        }
        if(Verify.verifyStuID(stuID) ==  true)
        {
            for(Student s: studentArrayList)
            {
                if(s.getStuID().equals(stuID))
                {
                    BigDecimal newMoney = s.getInitialAmount().add(addBalance);
                    s.setInitialAmount(newMoney);
                    System.out.println("充值成功");
                    break;
                }
            }
        }
    }

    //校园卡扣款
    public static void subMoney()
    {
        System.out.println("输入学号：");
        String stuID = sc.nextLine();
        while(stuID.isBlank())
        {
            System.out.println("该空不得为空");
            stuID = sc.nextLine();
        }
        System.out.println("输入要扣款的金额：");
        String balance = sc.nextLine();//删除的金额
        while (balance.isBlank())
        {
            System.out.println("该空不得为空");
            balance = sc.nextLine();
        }
        BigDecimal subBalance = new BigDecimal(balance);
        if(subBalance.compareTo(BigDecimal.ZERO) <= 0)
        {
            System.out.println("扣款金额必须大于0");
            return;
        }
        else
        {
            if(Verify.verifyStuID(stuID) ==  true)
            {
                for(Student s: studentArrayList)
                {
                    if(s.getStuID().equals(stuID))
                    {
                        if(s.getStuID().equals(stuID)){
                            //余额 >= 扣款金额：可以扣款
                            if(s.getInitialAmount().compareTo(subBalance) >= 0){
                                BigDecimal newMoney = s.getInitialAmount().subtract(subBalance);
                                s.setInitialAmount(newMoney);
                                System.out.println("扣款成功，剩余余额：" + newMoney);
                            }else{
                                //扣款金额 > 当前余额，进入这里
                                System.out.println("扣款失败！余额不足");
                            }
                            break;
                        }
                    }
                }
            }
            else
            {
                System.out.println("未找到该学生");
                return;
            }
        }
    }
}
