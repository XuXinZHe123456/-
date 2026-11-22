package com.xuxinzhe.Project.Class;

public enum CardState
{
    Normal(0,"正常"),
    Lost(1,"挂失"),
    Freeze(2,"冻结");

    private int code;//状态代码
    private String description;//状态描述

    CardState(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public int getCode() {return code;}

    public String getDescription() {return description;}
}
