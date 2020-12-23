package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;

import java.util.List;


public interface RouteDao {


    Integer getRouteCount(Integer cid);

    List<Route> findRouteByCid(Integer cid,Integer currentPage, Integer pageSize);

    Route getRouteById(Integer rid);


    Integer getRouteCountByname(String newRname);

    List<Route> getRouteByRname(String newRname, Integer newCurrentPage, Integer pageSize);

    Integer getRouteCountBynameAndCid(String newRname, Integer cid);

    List<Route> getRouteByRnameAndCid(String newRname, Integer cid, Integer newCurrentPage, Integer pageSize);
}
