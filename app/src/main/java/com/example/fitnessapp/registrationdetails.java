package com.example.fitnessapp;

public class registrationdetails {
    public String fullname,age,email,gender;

    public registrationdetails(){}

    public registrationdetails(String fullname, String age, String email,String gender) {
        this.fullname = fullname;
        this.age = age;
        this.email = email;
        this.gender = gender;

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
    public String getGender() {
        return gender;
    }

}
