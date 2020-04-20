package com.stu.ssm.controller;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SpringMvc-全局时间类型转换
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String dateStr) {
        Date date = null;
        if(dateStr!=null && dateStr.trim()!=""){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException e) {
                //e.printStackTrace();
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = sdf.parse(dateStr);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return date;
    }
}
