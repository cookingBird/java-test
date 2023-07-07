package com.example.java8test.user;


import java.io.Serializable;

public class User implements Serializable {
    public String name;
    private Integer age;
    protected String birth;
    String address;

    public User(String name, Integer age, String birth, String address) {
        this.name = name;
        this.age = age;
        this.birth = birth;
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.age = null;
        this.address = null;
    }

    public User(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
