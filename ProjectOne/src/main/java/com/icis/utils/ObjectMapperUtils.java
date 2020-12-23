package com.icis.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;

/**
 * xiejiajian     2020/10/29 10:36
 */
public class ObjectMapperUtils {
    private static ObjectMapper mapper=new ObjectMapper();
    //把一个对象转化为字符串功能
     public static  void writeObjectToJson(Object obj, HttpServletResponse response) {
         if (obj != null) {
             try {
                 response.getWriter().write(mapper.writeValueAsString(obj));
             } catch (Exception e) {
                 e.printStackTrace();

             }
         }
     }
}
