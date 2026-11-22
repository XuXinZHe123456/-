package com.xuxinzhe.Project.Function;

import java.util.Scanner;

public class LoginAndRegister //注册与登录
{
    static int wrongNum = 0;//错误处罚计数
    static final String AdminnName ="Admin";
    static final String AdminKey ="admin123456";
    static final int wrongMax = 3;
    // 锁定时长：30秒
    static final long LOCK_TIME = 30 * 1000L;
    // 锁定结束时间戳，0代表未锁定
    static long lockEnd = 0;

    static Scanner sc = new Scanner(System.in);
    public static void AdminLogin()
    {
        while(true)
        {
            long now = System.currentTimeMillis();
            // 判断是否还在锁定时间内
            if(now < lockEnd)
            {
                long remain = (lockEnd - now) / 1000;
                System.out.println("==== 锁定中，剩余" + remain + "秒，暂不能登录 ====");
                // 依然可以继续输入，不会阻塞
                System.out.println("输入管理员用户名：");
                String temp = sc.nextLine();
                continue;
            }
            System.out.println("输入管理员用户名：");
            String adminName = sc.nextLine();
            if(adminName.equals(AdminnName))
            {
                System.out.println("输入管理员密码：");
                String adminPass = sc.nextLine();
                if(adminPass.equals(AdminKey))
                {
                    System.out.println("登录成功");
                    break;
                }
                wrongNum++;
                System.out.println("用户名或密码错误");
            }
            else
            {
                wrongNum++;
                System.out.println("用户名或密码错误");
            }
            if(wrongNum >= wrongMax)
            {
                lockEnd = now + LOCK_TIME;
                wrongNum = 0;
                System.out.println("连续输错3次，锁定30秒！");
            }
        }
    }
}
