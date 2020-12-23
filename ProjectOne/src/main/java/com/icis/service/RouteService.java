package com.icis.service;

import com.icis.pojo.*;

import java.util.List;

public interface RouteService {



    //线路分页展示
    PageBean<Route> findRouteByCid(String cid, String _currentPage , String pageSize);

    //查看线路详情
    Route getRouteById(String rid);

    PageBean<Route> SelectRouteByRname(String cid, String newRname,String currentPage ,String pageSize);
}
