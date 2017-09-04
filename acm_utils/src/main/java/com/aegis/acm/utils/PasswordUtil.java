package com.aegis.acm.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordUtil {
    public static String encryptPassword(String password) {
        Md5Hash md5Hash = new Md5Hash(password, "H&Sd56^rKty!", 7);
        return md5Hash.toString();
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("123"));
    }
}
