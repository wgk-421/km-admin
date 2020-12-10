package com.km.main.admin.service.impl;

import com.km.main.admin.constant.KmSysConstant;
import com.km.main.admin.dao.IMenuDao;
import com.km.main.admin.mbgen.model.Menu;
import com.km.main.admin.service.IMenuService;
import com.km.main.admin.vo.MetaVO;
import com.km.main.admin.vo.RouterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/9 19:43
 */
@Service
public class MenuService implements IMenuService {

    @Autowired
    private IMenuDao menuDao;
    /**
     * 查询所有菜单
     * @return 菜单
     */
    @Override
    public List<Menu> findMenuAllList() {
        List<Menu> results = new ArrayList<>();
        List<Menu> menus = menuDao.findMenuAllList();
        if(!CollectionUtils.isEmpty(menus)) {
            results = this.getChildPerms(menus, 0);
        }
        return results;
    }

    /**
     * 获取所有子项菜单
     *
     * @param allMenu
     * @param parentId
     * @return
     */
    private List<Menu> getChildPerms(List<Menu> allMenu, int parentId) {
        List<Menu> routeList = new ArrayList<>();
        allMenu.stream().forEach(item -> {
            if(item.getParentId() == parentId) {
                processChild(allMenu, item);
                routeList.add(item);
            }
        });
        return routeList;
    }

    /**
     * 递归处理菜单（添加子菜单）
     *
     * @param allMenu 全部菜单
     * @param parentMenu 当前循环父菜单
     */
    private void processChild(List<Menu> allMenu, Menu parentMenu) {
        List<Menu> childList = this.getChildMenu(allMenu, parentMenu);
        parentMenu.setChildList(childList);
        childList.stream().forEach(item -> {
            if(this.hasChild(allMenu, item)) {
                processChild(allMenu, item);
            }
        });
    }

    /**
     * 获取子项菜单
     *
     * @param allMenu 全部菜单
     * @param parentMenu 当前菜单（父菜单）
     * @return parentMenu的子菜单
     */
    private List<Menu> getChildMenu(List<Menu> allMenu, Menu parentMenu) {
        List<Menu> childList = new ArrayList<>();
        if(CollectionUtils.isEmpty(allMenu)) {
            return childList;
        }
        allMenu.stream().forEach(item -> {
            if (item.getParentId() == parentMenu.getMenuId()) {
                childList.add(item);
            }
        });
        return childList;
    }

    /**
     * 判断是否子项
     *
     * @param allMenu
     * @param parentMenu
     * @return
     */
    private boolean hasChild(List<Menu> allMenu, Menu parentMenu) {
        return this.getChildMenu(allMenu, parentMenu).size() > 0 ? true : false;
    }

    /**
     * 根据菜单合并路由
     *
     * @param allMenu 所有菜单
     * @return 路由
     */
    @Override
    public List<RouterVO> mergeRouterByMenu(List<Menu> allMenu) {
        List<RouterVO> routers = new ArrayList<>();
        if(CollectionUtils.isEmpty(allMenu)) {
            return routers;
        }
        allMenu.stream().forEach(item -> {
            RouterVO router = new RouterVO();
            router.setHidden("1".equals(item.getHidden()));
            router.setName(StringUtils.capitalize(item.getUrl()));
            router.setPath(KmSysConstant.URL_ROOT + item.getUrl());
            router.setComponent(item.getComponentPath());
            router.setMeta(new MetaVO(item.getMenuName(), item.getIcon(), true));
            List<Menu> childList = item.getChildList();
            if(!CollectionUtils.isEmpty(childList) && KmSysConstant.MENU_TYPE_C.equals(item.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect(KmSysConstant.NO_REDIRECT);
                router.setChildren(mergeRouterByMenu(childList));
            } else if(isInsideLink(item)) {
                List<RouterVO> childrenList = new ArrayList<>();
                RouterVO children = new RouterVO();
                children.setPath(item.getUrl());
                children.setComponent(item.getComponentPath());
                children.setName(StringUtils.capitalize(item.getUrl()));
                children.setMeta(new MetaVO(item.getMenuName(), item.getIcon(), true));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        });
        return routers;
    }

    /**
     * 菜单类型的
     *
     * @param menu
     * @return
     */
    private boolean isInsideLink(Menu menu) {
        return menu.getParentId() == 0 && KmSysConstant.LINK_TYPE_INNER.equals(menu.getLinkType())
                && KmSysConstant.MENU_TYPE_M.equals(menu.getMenuType());
    }
}
