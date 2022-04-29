package com.example.mapper;

import com.example.entity.SysMenu;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chuan
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 插入操作
     * @param menu
     */
    void insert(SysMenu menu);

    /**修改操作
     * @param menu
     */
    void update(SysMenu menu);

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
    Page<SysMenu> findPage(String queryString);

    /**
     * 查询父级菜单
     * @return
     */
    List<SysMenu> findParent();
}
