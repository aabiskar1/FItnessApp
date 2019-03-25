package com.example.fitnessapp;

public class registrationDetailsStaff {
    public String fullname,age,email,reg_date,type;
    public registrationDetailsStaff(String fullname, String age, String email,String reg_date,String type){
        this.fullname = fullname;
        this.age = age;
        this.email = email;
        //this.gender = gender;
        this.reg_date = reg_date;
        this.type = type;
    }

    public String getFullname() {
        return fullname;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

   // public String getGender() {
    //    return gender;
   // }

    public String reg_date() {
        return reg_date;
    }
    public String type() {
        return type;
    }
}
