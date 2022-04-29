package com.example.service.impl;

import com.example.entity.SysMenu;
import com.example.mapper.SysMenuMapper;
import com.example.service.SysMenuService;
import com.example.util.*;
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
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private RedisUtil redisUtil;



    @Override
    public Result findPage(QueryInfo queryInfo) {
        log.info("开始菜单数据分页-->页码{},--->{}页数，---->查询内容{}",queryInfo.getPageNumber(),queryInfo.getPageSize(),queryInfo.getQueryString());
        //开始分页
        PageHelper.startPage(queryInfo.getPageNumber(),queryInfo.getPageSize());
        //去分页数据
        Page<SysMenu> page = sysMenuMapper.findPage(queryInfo.getQueryString());

        long total = page.getTotal();
        List<SysMenu> result = page.getResult();
        log.info("查询分页总数-->{}",total);

        log.info("分页结果--->{}",total);
        return new PageResult(total,result);
    }

    @Override
    public Result insert(SysMenu menu) {
        sysMenuMapper.insert(menu);
        //更新信息就去操作缓存里面的数据
        redisUtil.delKey("userInfo_" + SecurityUtil.getUsername());
        return Result.success("添加成功");
    }

    @Override
    public Result delete(Long id) {
        sysMenuMapper.delete(id);
        redisUtil.delKey("userInfo_" + SecurityUtil.getUsername());
        return Result.success("删除成功");
    }

    @Override
    public Result update(SysMenu menu) {
        sysMenuMapper.update(menu);
        redisUtil.delKey("userInfo_" + SecurityUtil.getUsername());
        return Result.success("修改成功");
    }

    @Override
    public Result findParent() {
        return Result.success("查询父级菜单成功",sysMenuMapper.findParent());
    }

}
