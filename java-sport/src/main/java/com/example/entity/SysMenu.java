package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "菜单id")
    private long id;
    @ApiModelProperty(value = "菜单路径")
    private String path;
    @ApiModelProperty(value = "菜单图标")
    private String icon;

    /**
     * 菜单标题
     */
    @ApiModelProperty(value = "菜单名称")
    private  String title;
    /**
     *前端组件
     */
    @ApiModelProperty(value = "组件")
    private String component;
    /**
     *菜单状态
     */
    @ApiModelProperty(value = "显示状态（0不显示）")
    private boolean status;
    /**
     * 父菜单
     * @JsonIgnore  不返回前端  ，前端取不到数据
     */
    @ApiModelProperty(value = "父级菜单ID")

    private long parentId;

    /**
     * 子菜单     *
     */
    @ApiModelProperty(value = "子菜单")

    private List<SysMenu> children;
}
