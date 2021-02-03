package com.km.demo.controller;

import com.km.common.generate.model.Menu;
import com.km.common.generate.model.MenuExample;
import com.km.common.response.CommonResultInfo;
import com.km.demo.service.IDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/3
 */
@Api(tags = "测试模板", value = "Test Demo")
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @ApiOperation(value = "查询菜单", notes = "查询系统全部菜单数据")
    @GetMapping("/list")
    public CommonResultInfo findList() {
        List<Menu> menuList =  demoService.findList(new MenuExample());
        return CommonResultInfo.buildSuccess(menuList);
    }

    @GetMapping("/menu")
    @ApiOperation(value = "条件查询菜单", notes = "筛选系统菜单数据")
    @ApiImplicitParam(name = "name", value = "菜单名称", defaultValue="100", required = true)
    public CommonResultInfo findMenu(@RequestParam(required = true) String name) {
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria().andMenuNameEqualTo(name);
        List<Menu> menuList =  demoService.findList(menuExample);
        return CommonResultInfo.buildSuccess(menuList);
    }
}
