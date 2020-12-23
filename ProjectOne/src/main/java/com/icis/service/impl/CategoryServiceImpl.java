package com.icis.service.impl;

import com.icis.dao.CategoryDao;
import com.icis.dao.impl.CategoryDaoImpl;
import com.icis.pojo.Category;
import com.icis.service.CategoryService;

import java.util.List;

/**
 * xiejiajian     2020/10/29 14:23
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao=new CategoryDaoImpl();

    @Override
    public Category getCategoryByCid(Integer cid) {
        return categoryDao.getCategoryByCid(cid);
    }

    @Override
    public List<Category> TopThreeCategory() {
        return categoryDao.TopThreeCategory();
    }
}
