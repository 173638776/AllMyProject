package com.icis.dao.impl;

import com.icis.dao.SellerDao;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * xiejiajian     2020/10/29 14:07
 */
public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate jdbcTemplate= JDBCUtils.getJDBCTemplate();
    @Override
    public Seller getSerllerBySid(Integer sid) {
        String sql="SELECT * FROM tab_seller WHERE sid=? ";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),sid);

    }
}
