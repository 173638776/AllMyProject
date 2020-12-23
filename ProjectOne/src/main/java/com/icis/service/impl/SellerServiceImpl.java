package com.icis.service.impl;

import com.icis.dao.SellerDao;
import com.icis.dao.impl.SellerDaoImpl;
import com.icis.pojo.Seller;
import com.icis.service.SellerService;

/**
 * xiejiajian     2020/10/29 14:08
 */
public class SellerServiceImpl implements SellerService {
    private SellerDao sellerDao=new SellerDaoImpl();
    @Override
    public Seller getSerllerBySid(Integer sid) {
        return sellerDao.getSerllerBySid(sid) ;
    }
}
