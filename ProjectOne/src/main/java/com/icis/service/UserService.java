package com.icis.service;

import com.icis.pojo.Category;
import com.icis.pojo.PageBean;
import com.icis.pojo.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    //检验是否存在用户名
    Integer checkUser(String username);
    //激活用户
    void ActivationUser(String code);
    //验证激活
    Integer checkActive(String username);
    //用户登录验证
    Integer loginUser(User user);
    //查询登录名字
    String findName(String username);

    List<Category> showCategory();

    String CategoryJedis();


}
