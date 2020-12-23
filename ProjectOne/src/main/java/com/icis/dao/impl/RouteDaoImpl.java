package com.icis.dao.impl;

import com.icis.dao.RouteDao;
import com.icis.pojo.Category;
import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * xiejiajian     2020/10/28 8:54
 */
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate jdbcTemplate=JDBCUtils.getJDBCTemplate();

    @Override
    public Integer getRouteCount(Integer cid) {
        String sql="SELECT COUNT(*) FROM tab_route  where cid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,cid);
    }

    @Override
    public List<Route> findRouteByCid(Integer cid,Integer currentPage, Integer pageSize) {
        String sql="SELECT * FROM tab_route WHERE cid=? ORDER BY price DESC LIMIT ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),cid,currentPage,pageSize);
    }

    @Override
    public Route getRouteById(Integer rid) {
        String sql="select * from tab_route where rid=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
    }

    @Override
    public Integer getRouteCountByname(String newRname) {
        String sql="SELECT COUNT(*) FROM tab_route WHERE INSTR(rname, ?) > 0 ";
        return jdbcTemplate.queryForObject(sql,Integer.class,newRname);
    }

    @Override
    public List<Route> getRouteByRname(String newRname, Integer CurrentPage, Integer pageSize) {

        String sql="SELECT * FROM tab_route WHERE INSTR(rname,?) > 0 ORDER BY price  DESC LIMIT ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),newRname,CurrentPage,pageSize);
    }

    @Override
    public Integer getRouteCountBynameAndCid(String newRname, Integer cid) {
        String sql="SELECT COUNT(*) FROM tab_route WHERE INSTR(rname, ?) > 0 and cid=? ";
        return jdbcTemplate.queryForObject(sql,Integer.class,newRname,cid);
    }

    @Override
    public List<Route> getRouteByRnameAndCid(String newRname, Integer cid, Integer CurrentPage, Integer pageSize) {
        String sql="SELECT * FROM tab_route WHERE INSTR(rname,?) > 0   and cid=? ORDER BY price  DESC LIMIT ?,?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Route.class),newRname,cid,CurrentPage,pageSize);
    }
}
