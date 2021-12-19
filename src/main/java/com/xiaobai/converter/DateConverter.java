package com.xiaobai.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:43
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName DateConverter
 */

/**
 * @author 终于白发始于青丝
 * 类型转换
 */
public class DateConverter implements Converter<String, Date> {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date convert(String source) {
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
