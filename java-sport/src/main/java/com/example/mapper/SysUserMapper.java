package com.example.mapper;

import com.example.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chuan
 */
@Mapper
public interface SysUserMapper {


    SysUser findByUsername(String username);

    /**
     * 查询所有数据
     * @return
     */
    List<SysUser> findAll();

}
