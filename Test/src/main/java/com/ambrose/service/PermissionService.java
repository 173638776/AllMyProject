package com.ambrose.service;

import java.util.List;

/**
 * @author xiejiajian
 * @createdDate 2021/5/21
 */
public interface PermissionService {
    List<String> findByRoleId(List<Integer> roleIds);
}
