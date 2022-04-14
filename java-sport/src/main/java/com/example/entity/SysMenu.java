package com.example.entity;

import lombok.Data;

import java.util.List;

/**
 * @author chuan
 *
 * id BIGINT(20) not null primary key auto_increment COMMENT'主键',
 * 	path varchar(100) comment'共享路径',
 * 	icon varchar(50) comment'共享图标',
 * 	title varchar(50) comment'共享名称',
 * 	component varchar(50) comment'菜单组件',
 * 	parent_id BIGINT(20) comment'父级菜单'
 */

@Data
public class SysMenu {

    private long id;

    private String path;

    private String icon;

    private  String title;

    private String component;
    /**
     * 子菜单
     */
    private List<SysMenu> children;
}
