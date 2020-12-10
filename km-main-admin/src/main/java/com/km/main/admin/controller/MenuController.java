package com.km.main.admin.controller;

import com.km.main.admin.common.CommonResultInfo;
import com.km.main.admin.mbgen.model.Menu;
import com.km.main.admin.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 菜单管理
 * @Author: GaoKunW
 * @Date: 2020/12/6 23:29
 */
@Controller
@RequestMapping("/menu")
@ResponseBody
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @GetMapping("/getRouters")
    public CommonResultInfo findMenuList() {
        List<Menu> menuList = menuService.findMenuAllList();
        return CommonResultInfo.success(menuService.mergeRouterByMenu(menuList));
    }
}
