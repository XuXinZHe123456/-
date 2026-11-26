package com.xuxinzhe.Project.Class;
import java.math.BigDecimal;
public class Student {
    private String schoolName = "阳光学院";//学生所属学校名字
    private String stuName;//学生姓名
    private String stuID;//学生学号，也相当于是学生卡的ID
    private BigDecimal initialAmount = new BigDecimal("20.00");//初始金额
    private String stuGrade;//学生的年级
    private String stuClass;//学生的班级
    private CardState cardState;//卡状态
    private int stuNum;//班级学生人数

    public Student() {}
    public Student(String schoolName,
                   String stuName, String stuID, BigDecimal initialAmount,
                   String stuGrade, String stuClass,  CardState cardStatus, int stuNum)
    {
        this.schoolName = schoolName;
        this.stuName = stuName;
        this.stuID = stuID;
        this.initialAmount = initialAmount;
        this.stuGrade = stuGrade;
        this.stuClass = stuClass;
        this.cardState = cardStatus;
        this.stuNum = stuNum;
    }

    //schoolName
    public String getSchoolName() {
        return schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }




    //stuName
    public String getStuName() {
        return stuName;
    }
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    //stuID
    public String getStuID() {
        return stuID;
    }
    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    //initialAmount
    public BigDecimal getInitialAmount() {
        return initialAmount;
    }
    public void setInitialAmount(BigDecimal initialAmount) {
        this.initialAmount = initialAmount;
    }

    //stuGrade
    public String getStuGrade() {
        return stuGrade;
    }
    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }

    //stuClass
    public String getStuClass() {return stuClass;}
    public void setStuClass(String stuClass) {this.stuClass = stuClass;}

    //cardStatus
    public String getCardStatus() { return cardState.toString(); }
    public void setCardStatus(CardState cardStatus) {this.cardState = cardStatus;}

    //stuNum

    public int getStuNum() {return stuNum;}
    public void setStuNum(int stuNum) {this.stuNum = stuNum;}
}
