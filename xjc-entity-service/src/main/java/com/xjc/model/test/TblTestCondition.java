package com.xjc.model.test;

import lombok.Data;

/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * <p>
 * Created by xiangjiangcheng on 2018/11/16 14:31.
 *
 * 查询条件实体类
 */
@Data
public class TblTestCondition {
    /**
     * 页码
     */
    private Integer page;

    /**
     * 条数
     */
    private Integer size;

    /**
     * 姓名
     */
    private String name;

}
