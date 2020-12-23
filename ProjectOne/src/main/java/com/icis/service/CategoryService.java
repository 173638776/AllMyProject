package com.icis.service;

import com.icis.pojo.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryByCid(Integer cid);

    List<Category> TopThreeCategory();

}
