package com.example.college_management_application;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Attendance {
    private String usn;
    private int sub1;
    private int sub2;
    private int sub3;
    private int sub4;
    private int sub5;
    private int sub6;
    private int sub7;
    private int sub8;
    private int sub9;
    private int sub10;

    // Empty constructor
    public Attendance() {
    }

    // Constructor with usn and attendance for subjects from sub1 to sub10
    public Attendance(String usn) {
        this.usn = usn;
        this.sub1 = 0;
        this.sub2 = 0;
        this.sub3 = 0;
        this.sub4 = 0;
        this.sub5 = 0;
        this.sub6 = 0;
        this.sub7 = 0;
        this.sub8 = 0;
        this.sub9 = 0;
        this.sub10 = 0;
    }

    // Getter and setter methods for usn and attendance for subjects from sub1 to sub10
    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public int getsub1() {
        return sub1;
    }

    public void setSub1(int sub1) {
        this.sub1 = sub1;
    }

    public int getsub2() {
        return sub2;
    }

    public void setSub2(int sub2) {
        this.sub2 = sub2;
    }

    public int getsub3() {
        return sub3;
    }

    public void setSub3(int sub3) {
        this.sub3 = sub3;
    }

    public int getsub4() {
        return sub4;
    }

    public void setSub4(int sub4) {
        this.sub4 = sub4;
    }

    public int getsub5() {
        return sub5;
    }

    public void setSub5(int sub5) {
        this.sub5 = sub5;
    }

    public int getsub6() {
        return sub6;
    }

    public void setSub6(int sub6) {
        this.sub6 = sub6;
    }

    public int getsub7() {
        return sub7;
    }

    public void setSub7(int sub7) {
        this.sub7 = sub7;
    }

    public int getsub8() {
        return sub8;
    }

    public void setSub8(int sub8) {
        this.sub8 = sub8;
    }

    public int getsub9() {
        return sub9;
    }

    public void setSub9(int sub9) {
        this.sub9 = sub9;
    }

    public int getsub10() {
        return sub10;
    }

    public void setSub10(int sub10) {
        this.sub10 = sub10;
    }

    /*
    public int getValue(String methodName) {
        try {
            Method method = this.getClass().getMethod(methodName);
            return (int) method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return -1;
        }
    }
     */

    public int get_sub_attendance(int subnum){
        switch (subnum){
            case 1:
                return getsub1();
            case 2:
                return getsub2();
            case 3:
                return getsub3();
            case 4:
                return getsub4();
            case 5:
                return getsub5();
            case 6:
                return getsub6();
            case 7:
                return getsub7();
            case 8:
                return getsub8();
            case 9:
                return getsub9();
            case 10:
                return getsub10();
        }

        return -1;
    }
}
