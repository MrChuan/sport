package com.example.service.impl;

import com.example.entity.SysPermission;
import com.example.mapper.SysPermissionMapper;
import com.example.service.SysPermissionService;
import com.example.util.PageResult;
import com.example.util.QueryInfo;
import com.example.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chuan
 */
@Service
@Slf4j
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;



    @Override
    public Result findPage(QueryInfo queryInfo) {
        log.info("开始权限数据分页-->页码{},--->{}页数，---->查询内容{}",queryInfo.getPageNumber(),queryInfo.getPageSize(),queryInfo.getQueryString());
        //开始分页
        PageHelper.startPage(queryInfo.getPageNumber(),queryInfo.getPageSize());
        //去分页数据
        Page<SysPermission> page = sysPermissionMapper.findPage(queryInfo.getQueryString());

        long total = page.getTotal();
        List<SysPermission> result = page.getResult();
        log.info("查询分页总数-->{}",total);

        log.info("分页结果--->{}",total);
        return new PageResult(total,result);
    }

    @Override
    public Result insert(SysPermission permission) {
        sysPermissionMapper.insert(permission);
        return Result.success("添加成功");
    }

    @Override
    public Result delete(Long id) {
        sysPermissionMapper.delete(id);
        return Result.success("删除成功");
    }

    @Override
    public Result update(SysPermission permission) {
        sysPermissionMapper.update(permission);
        return Result.success("修改成功");
    }

}
