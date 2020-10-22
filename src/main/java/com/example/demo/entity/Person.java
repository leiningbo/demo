package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: leiningbo
 * @description:
 * @date: Create in 16:35 2020/10/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private long id;
    private String name;
}
