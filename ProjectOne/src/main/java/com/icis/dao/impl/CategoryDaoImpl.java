package com.icis.dao.impl;

import com.icis.dao.CategoryDao;
import com.icis.pojo.Category;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * xiejiajian     2020/10/29 14:23
 */
public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate= JDBCUtils.getJDBCTemplate();
    @Override
    public Category getCategoryByCid(Integer cid) {
        String sql="SELECT * FROM tab_category WHERE cid=? ";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Category.class),cid);
    }

    @Override
    public List<Category> TopThreeCategory() {
        String sql="SELECT * FROM tab_category WHERE cid<4";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }

}
