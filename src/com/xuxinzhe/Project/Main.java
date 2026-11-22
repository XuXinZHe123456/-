package com.xuxinzhe.Project;

import com.xuxinzhe.Project.Function.User;
import com.xuxinzhe.Project.Function.LoginAndRegister;
import com.xuxinzhe.Project.Function.Menu;

import java.util.Scanner;

public class Main
{
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        Menu.initialMenu();
        Menu.initialMenuCHoose();
    }
}
