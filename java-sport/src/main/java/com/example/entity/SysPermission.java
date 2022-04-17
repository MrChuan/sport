package com.example.entity;

import lombok.Data;

/**
 * @author chuan
 *
 * user_id BIGINT(20) COMMENT'用户ID',
 * 	role_id BIGINT(20) COMMENT'角色ID'
 */

@Data
public class SysPermission {

    private long id;

    private String label;

    private String code;
}
