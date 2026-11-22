package com.xuxinzhe.Project.Function;

import java.util.Scanner;

public class Menu {
    static Scanner sc = new Scanner(System.in);

    //初始菜单
    public static void initialMenu()
    {
        System.out.println("=====初始菜单=====");
        System.out.println("[1]管理员登录");
        System.out.println("[0]退出系统");
        System.out.println("----------------");
        System.out.println("输入1或者0：");
    }

    //选择初始菜单
    public static void initialMenuCHoose()
    {
        while(true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    LoginAndRegister.AdminLogin();
                    // 登录成功后：先展示管理菜单，读取新输入，再处理
                    manageUserMenu();
                    manageUserMenuCHoose();
                    break;
                case 0:
                    System.out.println("退出系统");
                    System.exit(0);
                    break;
                default:
                    System.out.println("重新输入");
                    initialMenu();
                    initialMenuCHoose();
            }
        }
    }

    //管理用户菜单
    public static void manageUserMenu()
    {
        System.out.println("=====管理用户菜单=====");
        System.out.println("[1]增加用户");
        System.out.println("[2]展示用户");
        System.out.println("[3]修改用户");
        System.out.println("[4]返回上一级");
        System.out.println("----------------");
        System.out.println("输入1/2/3：");
    }

    //选择用户菜单
    public static void manageUserMenuCHoose() {
        while (true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    User.addStudent();
                    manageUserMenu();
                    break;
                case 2:
                    User.showStu();
                    manageUserMenu();
                    break;
                case 3:
                    User.updateStuName();
                    manageUserMenu();
                    break;
                case 4:
                    // 返回初始菜单：打印初始菜单，读取新选择
                    initialMenu();
                    initialMenuCHoose();
                    break;
                default:
                    System.out.println("请重新输入");
                    manageUserMenu();
            }

        }
    }
}
