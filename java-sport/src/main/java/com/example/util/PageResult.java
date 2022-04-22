package com.example.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;
/**
 * @author chuan
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResult extends Result implements Serializable {
    /**
     * 总记录数
     */
    @ApiModelProperty("查询总数")
    private long total;
    /**
     * 分页的数据
     */
    @ApiModelProperty("分页列表")
    private List<?> rows;

    public PageResult(long total, List<?> list) {
        this.setFlag(true);
        this.setMessage("分页查询成功！");
        this.total = total;
        this.rows = list;
    }

    public static Result PageResult(long total, List<?> list){
        return new PageResult(total, list);
    }
}


