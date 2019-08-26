package com.example.demo.entity;

import lombok.Data;

import java.util.List;


/**
 * 学生类
 * @author leiningbo
 * @date 09点23分 2019-08-26
 *
 */
@Data
public class Student {
    private String studentId;
    private String name;
    private String sex;
    private List<Stationery> stationerys;
    private int age;

    public Student(String studentId, String name, String sex, List<Stationery> stationerys, int age) {
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.stationerys = stationerys;
        this.age = age;
    }
}
