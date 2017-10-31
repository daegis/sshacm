package com.aegis.acm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA on 2017/10/30 22:46.
 *
 * @author XIAN_Yingda
 */
public class TimeUtil {

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    public static void main(String[] args) {
        System.out.println(getCurrentTime());
    }
}
