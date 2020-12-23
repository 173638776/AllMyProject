package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * xiejiajian     2020/10/26 10:34
 */
public class UserDaoImpl implements UserDao {
   private JdbcTemplate jdbcTemplate= JDBCUtils.getJDBCTemplate();
    @Override
    public void addUser(User user) {
        String sql="insert  into tab_user values (null,?,?,?,?,?,?,?,?,? )";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());

    }

    @Override
    public Integer checkUser(String username) {
        String sql ="select count(*) from tab_user where username=?";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return integer;
    }

    @Override
    public void ActivationUser(String code) {
        String sql="update tab_user set status=? where code=?";
        jdbcTemplate.update(sql,"Y",code);
    }

    @Override
    public Integer checkActive(String username) {
        String sql ="select count(*) from tab_user where username=? AND STATUS='Y' ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return integer;
    }

    @Override
    public Integer loginUser(User user) {
        String sql ="select count(*) from tab_user where username=? AND password=? ";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(),user.getPassword());
        return integer;
    }

    @Override
    public String findName(String username) {
        String sql="select name from tab_user where username=?";
        return jdbcTemplate.queryForObject(sql, String.class, username);

    }

    @Override
    public List<Category> showCategory() {
        String sql="select * from tab_category order by  cid";
        List<Category> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return list;
    }
}
