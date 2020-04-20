package com.stu.ssm.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils implements Serializable {

    /**
     * 日期类型转字符串
     * @param date
     * @param patt
     * @return
     */
    public static String DateToString(Date date,String patt){
        SimpleDateFormat sf  = new SimpleDateFormat(patt);
        String format = sf.format(date);
        return format;
    }

    /**
     * 字符串转日期类型
     * @param date
     * @param patt
     * @return
     * @throws ParseException
     */
    public static Date StringToDate(String date,String patt) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(patt);
        Date date1 = sf.parse(date);
        return date1;
    }
}
