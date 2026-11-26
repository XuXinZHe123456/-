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
                    manageMenu();
                    manageMenuChoose();
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

    //管理菜单：功能模块入口
    public static void manageMenu()
    {
        System.out.println("=====管理菜单=====");
        System.out.println("[1]用户模块");
        System.out.println("[2]财政模块");
        System.out.println("[3]状态模块");
        System.out.println("[4]操作记录");
        System.out.println("[5]保存数据");
        System.out.println("[0]返回上一级");
        System.out.println("----------------");
        System.out.println("输入0~5：");
    }

    //选择管理菜单
    public static void manageMenuChoose()
    {
        while (true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    userMenu();
                    userChoose();
                    manageMenu();
                    break;
                case 2:
                    financeMenu();
                    financeChoose();
                    manageMenu();
                    break;
                case 3:
                    statusMenu();
                    statusChoose();
                    manageMenu();
                    break;
                case 4:
                    // 操作记录里既有财政的操作，也有状态的操作，所以放在管理菜单
                    User.showLog();
                    manageMenu();
                    break;
                case 5:
                    if(File.saveData())
                    {
                        System.out.println("数据已保存");
                    }
                    Database.saveData();
                    manageMenu();
                    break;
                case 0:
                    // 返回初始菜单：打印初始菜单，读取新选择
                    initialMenu();
                    initialMenuCHoose();
                    break;
                default:
                    System.out.println("请重新输入");
                    manageMenu();
            }
        }
    }

    //用户模块菜单
    public static void userMenu()
    {
        System.out.println("=====用户模块=====");
        System.out.println("[1]增加学生");
        System.out.println("[2]删除学生");
        System.out.println("[3]修改学生名字");
        System.out.println("[4]修改年级/班级/学号");
        System.out.println("[5]查询学生");
        System.out.println("[6]展示全部学生");
        System.out.println("[7]班级名单");
        System.out.println("[8]显示班级人数");
        System.out.println("[0]返回管理菜单");
        System.out.println("----------------");
        System.out.println("输入0~8：");
    }

    //选择用户模块
    public static void userChoose()
    {
        while (true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    User.addStudent();
                    userMenu();
                    break;
                case 2:
                    User.deleteStudent();
                    userMenu();
                    break;
                case 3:
                    User.updateStuName();
                    userMenu();
                    break;
                case 4:
                    User.updateStuInfo();
                    userMenu();
                    break;
                case 5:
                    User.queryStudent();
                    userMenu();
                    break;
                case 6:
                    User.showStu();
                    userMenu();
                    break;
                case 7:
                    User.showClassList();
                    userMenu();
                    break;
                case 8:
                    User.showClassNum();
                    userMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("请重新输入");
                    userMenu();
            }
        }
    }

    //财政模块菜单
    public static void financeMenu()
    {
        System.out.println("=====财政模块=====");
        System.out.println("[1]充值");
        System.out.println("[2]消费");
        System.out.println("[0]返回管理菜单");
        System.out.println("----------------");
        System.out.println("输入0~2：");
    }

    //选择财政模块
    public static void financeChoose()
    {
        while (true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    User.recharge();
                    financeMenu();
                    break;
                case 2:
                    User.consume();
                    financeMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("请重新输入");
                    financeMenu();
            }
        }
    }

    //状态模块菜单
    public static void statusMenu()
    {
        System.out.println("=====状态模块=====");
        System.out.println("[1]冻结卡");
        System.out.println("[2]解冻卡");
        System.out.println("[0]返回管理菜单");
        System.out.println("----------------");
        System.out.println("输入0~2：");
    }

    //选择状态模块
    public static void statusChoose()
    {
        while (true)
        {
            System.out.println("输入选择：");
            int choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    User.freezeCard();
                    statusMenu();
                    break;
                case 2:
                    User.unfreezeCard();
                    statusMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("请重新输入");
                    statusMenu();
            }
        }
    }
}
