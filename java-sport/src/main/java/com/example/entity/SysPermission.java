package com.example.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author chuan
 *
 * user_id BIGINT(20) COMMENT'用户ID',
 * 	role_id BIGINT(20) COMMENT'角色ID'
 */


@Data
public class SysPermission {

    @ApiModelProperty(value = "主键")
    private long id;

    @ApiModelProperty(value = "权限名称")
    private String label;

    @ApiModelProperty(value = "权限便签值")
    private String code;

    @ApiModelProperty(value = "启用状态")
    private boolean status;
}
