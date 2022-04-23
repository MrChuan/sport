package com.example.controller;

import com.example.entity.SysPermission;
import com.example.service.SysPermissionService;
import com.example.util.QueryInfo;
import com.example.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jnlp.PersistenceService;

/**
 * @author chuan
 */
@RestController
@Api(tags = "权限数据")
@RequestMapping("/permission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @PostMapping("/findPage")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return sysPermissionService.findPage(queryInfo);
    }

    /**
     * 添加
     */
    @PostMapping("/insert")
    @ApiOperation(value = "权限添加")
    public Result insert(@RequestBody SysPermission permission){
        return sysPermissionService.insert(permission);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "权限修改")
    public Result update(@RequestBody SysPermission permission){
        return sysPermissionService.update(permission);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "权限删除")
    public Result delete(@PathVariable("id") Long id){
        return sysPermissionService.delete(id);
    }

}
