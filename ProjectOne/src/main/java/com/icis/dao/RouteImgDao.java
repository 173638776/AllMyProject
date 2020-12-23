package com.icis.dao;

import com.icis.pojo.RouteImg;

import java.util.List;

public interface RouteImgDao {


    List<RouteImg> getRouteImgByRid(Integer rid);
}
