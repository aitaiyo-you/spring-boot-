package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String NowDate(){
        return new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    }
}
