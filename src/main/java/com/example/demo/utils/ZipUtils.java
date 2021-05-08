package com.example.demo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;

/**
 * @author lnb
 * @date 2021/4/16 17:55
 * @description
 */
public class ZipUtils {


    public static String getHtmlByPathName(String pathName){
        return null;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("E://abo/html/1(1).html");

        StringBuilder sb = new StringBuilder();

        FileInputStream fis = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String s = "";
        while ((s = br.readLine()) != null) {
            sb.append(s);
//            sb.append(s).append("\n");
        }
        System.out.println("原文："+sb);
        System.out.println("去空格：" + sb.toString().replaceAll(" ", ""));
        System.out.println("去除console.log:" + sb.toString().replaceAll("console\\.log\\(.*?\\);", ""));

    }

}
