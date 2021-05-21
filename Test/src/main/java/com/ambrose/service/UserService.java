package com.ambrose.service;

import com.ambrose.domain.User;

/**
 * @author xiejiajian
 * @createdDate 2021/5/21
 */
public interface UserService {
    User findByAccount(String account);
}
