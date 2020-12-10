package com.km.main.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/9 21:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaVO {
    /**
     * 侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 路由的图标（路径src/assets/icons/svg）
     */
    private String icon;

    /**
     * true：不会被 <keep-alive>缓存
     */
    private boolean noCache;
}
