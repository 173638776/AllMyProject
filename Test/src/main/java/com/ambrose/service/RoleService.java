package com.ambrose.service;

import com.ambrose.domain.Role;

import java.util.List;

/**
 * @author xiejiajian
 * @createdDate 2021/5/21
 */
public interface RoleService {
    List<Role> findRoleByUserId(Integer id);
}
