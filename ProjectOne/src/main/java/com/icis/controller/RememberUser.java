package com.icis.controller;

import com.icis.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/rememberUser")
public class RememberUser extends HttpServlet {
    private User user=new User();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieStatus = request.getParameter("cookieStatus");
        if (cookieStatus.equals("1")){
            Map<String, String[]> parameterMap = request.getParameterMap();
            try {
                BeanUtils.populate(user,parameterMap);
                if (!user.getPassword().equals("")&&!user.getUsername().equals("")){
                    String loginInfo = user.getUsername()+","+user.getPassword();
                    Cookie userCookie=new Cookie("loginInfo",loginInfo);
                    userCookie.setMaxAge(30*24*60*60);   //存活期为一个月 30*24*60*60
                    response.addCookie(userCookie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cookieStatus.equals("2")) {
            Cookie newCookie=new Cookie("loginInfo","");; //假如要删除名称为username的Cookie
           /* newCookie.setMaxAge(0); //立即删除型
            newCookie.setPath("/"); //项目所有目录均有效，这句很关键，否则不敢保证删除*/
            response.addCookie(newCookie); //重新写入，将覆盖之前
        }




        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
