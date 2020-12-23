package com.icis.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * xiejiajian     2020/10/28 9:44
 */
//封装所以的servlet
public class BaseServlet extends HttpServlet {
    //子类重写父类的service 方法

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //1.获得请求的请求的路径
        String requestURI = req.getRequestURI();

        //1.2获得对应的servlet的字节码
       // System.out.println("当前servlet："+this);
        Class servletClass = this.getClass();

        //2.2截取字节码获得方法名
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
      //  System.out.println("获得方法名是"+methodName);
        //3.获得类对应的成员方法
        try {
            Method method = servletClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //调用方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
