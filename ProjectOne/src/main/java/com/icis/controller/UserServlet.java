package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * xiejiajian     2020/10/28 10:00
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    private User user=new User();


    public void updateRoute(HttpServletRequest request, HttpServletResponse response) {
    }




    //激活用户
    public void activeUser(HttpServletRequest request, HttpServletResponse response) {
        String uuidCode = request.getParameter("uuidCode");
        userService.ActivationUser(uuidCode);
        try {
            response.sendRedirect("http://192.168.4.30:8080/travel/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证用户
    public void checkUser(HttpServletRequest request, HttpServletResponse response)  {
        try {
            String username = request.getParameter("username");
            if (!request.getParameter("username").equals("")&&request.getParameter("checkActive")==null){
                Integer integer = userService.checkUser(username);
                response.getWriter().write(mapper.writeValueAsString(integer));
            }
            //当用户名存在时候 进行是否激活查询
            if (request.getParameter("checkActive")!=null&&!request.getParameter("username").equals("")&&request.getParameter("checkActive").equals("1")){
                Integer integer =userService.checkActive(username);
                response.getWriter().write(mapper.writeValueAsString(integer));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    //用户登录
    public void loginUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();
       /* Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName()+"--"+cookie.getValue());
        }*/
        try {
            BeanUtils.populate(user,parameterMap);
            Integer integer =userService.loginUser(user);
            if (integer>0){

                String username = userService.findName(user.getUsername());
                request.getSession().setAttribute("username",username);
            }
            response.getWriter().write(mapper.writeValueAsString(integer));
        } catch (Exception e) {
        }
    }

    //退出登录
    public void exitLogin(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("username");
    }


    //用户注册
    public void registerUser(HttpServletRequest request, HttpServletResponse response) {
        try {
        //判断验证码的
        if (request.getParameter("checkcode")!=null&&request.getParameter("checkcode").equals("1")){
            Object checkcode_server = request.getSession().getAttribute("CHECKCODE_SERVER");
            response.getWriter().write(mapper.writeValueAsString(checkcode_server));
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        //状态码
            String uuid = UuidUtil.getUuid();
            BeanUtils.populate(user,parameterMap);
            user.setCode(uuid);
            user.setStatus("N");
            userService.addUser(user);
            //如果不写好像不能跳转页面
            response.getWriter().write( mapper.writeValueAsString("注册成功！"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //展示分类
    public void showCategory(HttpServletRequest request, HttpServletResponse response) {
        Object username = request.getSession().getAttribute("username");
        //判断是否已经登录
        if (username!=null){
            String s = userService.CategoryJedis();
            try {
                response.getWriter().write(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //回显用户名
    public void showUserName(HttpServletRequest request, HttpServletResponse response) {
        Object username = request.getSession().getAttribute("username");
        try {
            response.getWriter().write(mapper.writeValueAsString(username));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
