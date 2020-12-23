package com.icis.dao;

import com.icis.pojo.Category;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    //检验是否存在用户名
    Integer checkUser(String username);
    //激活用户
    void ActivationUser(String code);

    Integer checkActive(String username);

    Integer loginUser(User user);

    String findName(String username);

    List<Category> showCategory();
}
