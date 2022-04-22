package com.example.service;

import com.example.entity.SysPermission;
import com.example.util.QueryInfo;
import com.example.util.Result;

/**
 * @author chuan
 */

public interface SysPermissionService {

    /**
     * 分页查询
     * @param queryInfo
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 插入
     * @param permission
     * @return
     */
    Result insert(SysPermission permission);

    /**
     * 删除
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改权限数据
     * @param permission
     * @return
     */
    Result update(SysPermission permission);


}
