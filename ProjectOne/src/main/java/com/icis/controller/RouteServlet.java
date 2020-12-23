package com.icis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.*;
import com.icis.service.CategoryService;
import com.icis.service.RouteService;
import com.icis.service.UserService;
import com.icis.service.impl.CategoryServiceImpl;
import com.icis.service.impl.RouteServiceImpl;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * xiejiajian     2020/10/28 9:50
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    //创建一个service层的类对象
    private RouteService routeService=new RouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    private CategoryService categoryService=new CategoryServiceImpl();



   //根据获得id前三的分类
    public void topThreeCategory(HttpServletRequest request, HttpServletResponse response) {
        List<Category> list = categoryService.TopThreeCategory();
        //System.out.println(list);
        try {
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    //根据cid获得旅游线路
    public void getRouteByCid(HttpServletRequest request, HttpServletResponse response) {
        //当前路线
        String cid = request.getParameter("cid");
        //当前页
         String  currentPage = request.getParameter("currentPage");
        //每页大小为5  暂时写死 有需求在改动
        String pageSize="5";
        PageBean<Route> pageBean = routeService.findRouteByCid(cid, currentPage, pageSize);
       // System.out.println(pageBean);
        try {
            String  routesStr = mapper.writeValueAsString(pageBean);
            response.getWriter().write(routesStr);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //搜索线路
    public void selectRouteByRname(HttpServletRequest request, HttpServletResponse response) {
        //当前页
        String  currentPage = request.getParameter("currentPage");
        //每页大小为5  暂时写死 有需求在改动
        String pageSize="5";
        //搜索字段
        String  newRname = request.getParameter("newRname");
        //当前分类
        String  cid = request.getParameter("cid");
        PageBean<Route> pageBean=  routeService.SelectRouteByRname(cid,newRname,currentPage,pageSize);
       // System.out.println(SelPageBean);
        try {
            String  routesStr = mapper.writeValueAsString(pageBean);
            response.getWriter().write(routesStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //查看详情
    public void getRouteByRouteId(HttpServletRequest request, HttpServletResponse response) {
        String  rid = request.getParameter("rid");
        Route route=  routeService.getRouteById(rid);
        //System.out.println(route);
        try {
            response.getWriter().write(mapper.writeValueAsString(route));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
