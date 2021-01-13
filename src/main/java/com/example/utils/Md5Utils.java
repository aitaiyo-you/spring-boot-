package com.example.utils;

import org.springframework.util.DigestUtils;

public class Md5Utils {
    public static String md5(String str){
        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
    public static void main(String[] args) {
        System.out.println(md5("123"));
    }
}
