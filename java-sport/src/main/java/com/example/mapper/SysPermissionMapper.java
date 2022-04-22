package com.example.mapper;

import com.example.entity.SysPermission;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chuan
 */
@Mapper
public interface SysPermissionMapper {

    /**
     * 插入操作
     * @param permission
     */
    void insert(SysPermission permission);

    /**修改操作
     * @param permission
     */
    void update(SysPermission permission);

    /**
     * 删除操作
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    Page<SysPermission> findPage(String queryString);


}
