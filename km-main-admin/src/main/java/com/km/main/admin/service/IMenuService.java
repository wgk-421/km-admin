package com.km.main.admin.service;

import com.km.main.admin.mbgen.model.Menu;
import com.km.main.admin.vo.RouterVO;

import java.util.List;

/**
 * @Description: 菜单接口
 * @Author: GaoKunW
 * @Date: 2020/12/9 19:43
 */
public interface IMenuService {
    /**
     * 查询所有菜单
     * @return 菜单
     */
    List<Menu> findMenuAllList();

    /**
     * 根据菜单合并路由
     *
     * @param allMenu 所有菜单
     * @return 路由
     */
    List<RouterVO> mergeRouterByMenu(List<Menu> allMenu);
}
