package com.example.fitnessapp;

public class registrationdetails {
    public String fullname,age,email,gender,reg_date,type;
        //Empty constructer
    public registrationdetails(){}

    public registrationdetails(String fullname, String age, String email,String reg_date,String type) {
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
  //  public String getGender() {
    //    return gender;
   // }
    public String getReg_date() {
        return reg_date;
    }
    public String type() {
        return type;
    }

}
