package com.hu.video.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String currentDateFormatString(){
        Date date = new Date();	//创建一个date对象
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss"); //定义格式
        return format.format(date);
    }
}
