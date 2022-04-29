package com.example.controller;

import com.example.entity.SysMenu;
import com.example.service.SysMenuService;
import com.example.util.QueryInfo;
import com.example.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chuan
 */
@RestController
@Api(tags = "权限数据")
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/findParent")
    @ApiOperation(value = "查询父级菜单")
    public Result findParent(){
        return sysMenuService.findParent();
    }

    @PostMapping("/findPage")
    @ApiOperation(value = "分页查询")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return sysMenuService.findPage(queryInfo);
    }

    /**
     * 添加
     */
    @PostMapping("/insert")
    @ApiOperation(value = "权限添加")
    public Result insert(@RequestBody SysMenu menu){
        return sysMenuService.insert(menu);
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @ApiOperation(value = "权限修改")
    public Result update(@RequestBody SysMenu menu){
        return sysMenuService.update(menu);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "权限删除")
    public Result delete(@PathVariable("id") Long id){
        return sysMenuService.delete(id);
    }

}
