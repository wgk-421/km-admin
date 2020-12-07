package com.km.main.admin.controller;

import com.km.main.admin.mbgen.mapper.MenuMapper;
import com.km.main.admin.mbgen.model.Menu;
import com.km.main.admin.mbgen.model.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/6 23:29
 */
@Controller
@RequestMapping("/menu")
@ResponseBody
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;
    @GetMapping("/getRouters")
    public List<Menu> findMenuList() {
        MenuExample menuExample = new MenuExample();
        List<Menu> result = menuMapper.selectByExample(menuExample);
        return result;
    }
}
