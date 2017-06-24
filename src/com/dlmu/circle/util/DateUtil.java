package com.dlmu.circle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cf on 2017/3/25.
 */
public class DateUtil {
    public static String formatDate(Date date,String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result= sdf.format(date);
        }
        return result;
    }
    public static Date formatString(String str,String format) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        return sdf.parse(str);
    }
}
