package com.icis.service.impl;

import com.icis.dao.RouteDao;
import com.icis.dao.impl.RouteDaoImpl;
import com.icis.pojo.*;
import com.icis.service.CategoryService;
import com.icis.service.RouteImgService;
import com.icis.service.RouteService;
import com.icis.service.SellerService;

import java.util.ArrayList;
import java.util.List;

/**
 * xiejiajian     2020/10/28 9:13
 */
public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao=new RouteDaoImpl();
    private SellerService sellerService=new SellerServiceImpl();
    private RouteImgService routeImgService=new RouteImgServiceImpl();
    private CategoryService categoryService=new CategoryServiceImpl();
    private   List<String> wordsStr=new ArrayList<>();
    private   PageBean<Route> pageBean=new PageBean<>();
    //分页展示
    @Override
    public PageBean<Route> findRouteByCid(String _cid, String _currentPage ,String _pageSize) {
        //第一个条件是过滤currentPage=(后面不赋值) 第二个条件是过滤前端没有currentPage  所以两个都要成立
        if ("".equals(_currentPage)||_currentPage==null){
            _currentPage="1";
        }
        if ("null".equals(_cid)||_cid==null||"".equals(_cid)){
            _cid="1";
        }

        //设置好 当前页 和页面的大小  还有当前路线
        Integer currentPage = Integer.parseInt(_currentPage);
        Integer pageSize = Integer.parseInt(_pageSize);
        Integer cid = Integer.parseInt(_cid);

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);

        //查询所有的路线
        Integer total = routeDao.getRouteCount(cid);
        //设置总记录数
        pageBean.setTotalCount(total);
        //设置总页数
        Integer totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        pageBean.setTotalPage(totalPage);


        //2.调用dao 查询当前页数据
        Integer newCurrentPage=(currentPage-1)*pageSize;
        List<Route> dataList =routeDao.findRouteByCid(cid,newCurrentPage,pageSize);
        pageBean.setDataList(dataList);
        return pageBean;
    }


    //查看详情
    @Override
    public Route getRouteById(String rid1) {
        if ("".equals(rid1)||rid1==null){
            rid1="1";
        }

        Integer rid= Integer.valueOf(rid1);
        Route route = routeDao.getRouteById(rid);


        //根据rid查询对应的分类
       Category category= categoryService.getCategoryByCid(route.getCid());
       route.setCategory(category);

        //根据rid 查询对应的seller
        Seller seller=sellerService.getSerllerBySid(route.getSid());
        route.setSeller(seller);

        //根据rid 查询对应的 List<RouteImg>
        List<RouteImg> routeImgs= routeImgService.getRouteImgByRid(rid);
        route.setRouteImgList(routeImgs);
        return route;
    }


    @Override
    public PageBean<Route> SelectRouteByRname(String cid, String newRname,String _currentPage ,String _pageSize) {

        //存值
        wordsStr.add(newRname);
        //当为空值的时候 获取以前存的值
        if ("".equals(newRname)||newRname==null||newRname.equals("null")){
            newRname= wordsStr.get(0);
        }else {
            //重新输入值的时候  获取的是当前值
            wordsStr.clear();
            wordsStr.add(newRname);
        }
        // System.out.println(newRname+"newRanme");
        //第一个条件是过滤currentPage=(后面不赋值) 第二个条件是过滤前端没有currentPage  所以两个都要成立
        if ("".equals(_currentPage)||_currentPage==null){
            _currentPage="1";
        }
        Integer currentPage = Integer.parseInt(_currentPage);
        Integer pageSize = Integer.parseInt(_pageSize);

        //去除所有空格
        String rname = newRname.replaceAll(" ", "");
        //如果为空则表明是在没有选择 分类的时候进行查询

        if (cid.equals("null")||cid==null||"".equals(cid)){
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);
            //查询 没有cid 的总记录数
            Integer total = routeDao.getRouteCountByname(rname);
            //设置总记录数
            pageBean.setTotalCount(total);
            //设置总页数
            Integer totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
            pageBean.setTotalPage(totalPage);

            //2.查询当前页数据
            Integer newCurrentPage=(currentPage-1)*pageSize;
            List<Route> dataList =routeDao.getRouteByRname(rname,newCurrentPage,pageSize);
            pageBean.setDataList(dataList);
        }else {
            Integer _cid = Integer.parseInt(cid);
            pageBean.setCurrentPage(currentPage);
            pageBean.setPageSize(pageSize);

            //查询有cid 的记录数
            Integer total=  routeDao.getRouteCountBynameAndCid(rname,_cid);

            //设置总记录数
            pageBean.setTotalCount(total);
            //设置总页数
            Integer totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
            pageBean.setTotalPage(totalPage);

            //2. 查询当前页数据
            Integer newCurrentPage=(currentPage-1)*pageSize;
            List<Route> dataList =routeDao.getRouteByRnameAndCid(rname,_cid,newCurrentPage,pageSize);
            pageBean.setDataList(dataList);
        }

        return pageBean;
    }
}
