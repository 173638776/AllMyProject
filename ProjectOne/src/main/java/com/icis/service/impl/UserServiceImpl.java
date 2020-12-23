package com.icis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Category;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.utils.JedisUtil;
import com.icis.utils.MailUtils;
import com.icis.utils.Md5Util;
import redis.clients.jedis.Jedis;

import java.util.List;
/**
 * xiejiajian     2020/10/26 10:32
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    private Jedis jedis=JedisUtil.getJedis();
    private ObjectMapper mapper=new ObjectMapper();
    @Override
    public void addUser(User user) {
        try {
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userDao.addUser(user);
        MailUtils.sendMail(user.getEmail(),"您好！这是中软旅游项目激活链接，您点击链接即可完成激活："+"http://192.168.4.30:8080/travel/user/activeUser?uuidCode="+user.getCode(),"激活邮件");
    }

    @Override
    public Integer checkUser(String username) {
        return userDao.checkUser(username);
    }

    @Override
    public void ActivationUser(String code) {
       userDao.ActivationUser(code);
    }

    @Override
    public Integer checkActive(String username) {
        return userDao.checkActive(username);
    }

    @Override
    public Integer loginUser(User user) {
        try {
            //加密
            user.setPassword(Md5Util.encodeByMd5(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return   userDao.loginUser(user);
    }

    @Override
    public String findName(String username) {
        return userDao.findName(username);
    }

    @Override
    public List<Category> showCategory() {
        return userDao.showCategory();
    }

    //把分类写进jedis
    @Override
    public String CategoryJedis() {
        String listCategory = jedis.get("listCategory");
        if (listCategory==null){
            //从数据库中查询
            List<Category> listCategorys = this.showCategory();
            try {
                jedis.set("listCategory",mapper.writeValueAsString(listCategorys));
                listCategory = jedis.get("listCategory");
                //System.out.println("从数据库取数据！");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return listCategory;
    }
    }
