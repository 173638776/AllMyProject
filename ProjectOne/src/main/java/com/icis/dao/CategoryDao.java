package com.icis.dao;

import com.icis.pojo.Category;

import java.util.List;

public interface CategoryDao {
    Category getCategoryByCid(Integer cid);

    List<Category> TopThreeCategory();

}
