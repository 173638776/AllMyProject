package com.icis.service.impl;

import com.icis.dao.RouteImgDao;
import com.icis.dao.impl.RouteImgDaoImpl;
import com.icis.pojo.RouteImg;
import com.icis.service.RouteImgService;
import com.icis.service.RouteService;

import java.util.List;

/**
 * xiejiajian     2020/10/29 14:17
 */
public class RouteImgServiceImpl implements RouteImgService {
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    @Override
    public List<RouteImg> getRouteImgByRid(Integer rid) {
        return routeImgDao.getRouteImgByRid(rid);
    }
}
