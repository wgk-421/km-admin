package com.km.main.admin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/9 21:42
 * JsonInclude.Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class RouterVO {
    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 路由是否隐藏(1:隐藏 0:显示)
     */
    private boolean hidden;

    /**
     * 重定向地址，noRedirect：不可跳转
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 有children路由，自动会变成嵌套
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVO meta;

    /**
     * 子路由
     */
    private List<RouterVO> children;
}
