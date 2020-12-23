package com.icis.dao.impl;

import com.icis.dao.RouteImgDao;
import com.icis.pojo.RouteImg;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * xiejiajian     2020/10/29 14:16
 */
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate jdbcTemplate= JDBCUtils.getJDBCTemplate();

    @Override
    public List<RouteImg> getRouteImgByRid(Integer rid) {
        String sql="SELECT * FROM tab_route_img WHERE rid=?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
