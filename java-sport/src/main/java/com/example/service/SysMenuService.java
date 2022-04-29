package com.example.service;

import com.example.entity.SysMenu;
import com.example.util.QueryInfo;
import com.example.util.Result;

/**
 * @author chuan
 */

public interface SysMenuService {

    /**
     * 分页查询
     * @param queryInfo
     * @return
     */
    Result findPage(QueryInfo queryInfo);

    /**
     * 插入
     * @param menu
     * @return
     */
    Result insert(SysMenu menu);

    /**
     * 删除
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改权限数据
     * @param menu
     * @return
     */
    Result update(SysMenu menu);

    Result findParent();


}
