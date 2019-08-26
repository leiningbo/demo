package com.example.demo.entity;

import lombok.Data;

/**
 * 文具类
 * @author leininhgbo
 * @date 2019-08-26 09点56分
 */
@Data
public class Stationery {
    private String pen;
    private String notebook;

    public Stationery(String pen, String notebook) {
        this.pen = pen;
        this.notebook = notebook;
    }
}
