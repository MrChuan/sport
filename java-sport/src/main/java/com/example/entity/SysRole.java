package com.example.entity;

import lombok.Data;

import java.util.List;

/**
 *
 * @author chuan
 *
 *
 *  id bigint(20) not null primary key auto_increment comment'主键',
 * 	lable varchar(50) comment'角色描述',
 * 	code varchar(10) comment'角色对应标签值'
 */

@Data
public class SysRole {

    private long id;

    private String lable;

    private String code;

    /**
     * 角色对应的菜单列表
     */
    private List<SysMenu> menus;
    /**
     * 数据权限
     */
    private List<SysPermission> permissions;


}
