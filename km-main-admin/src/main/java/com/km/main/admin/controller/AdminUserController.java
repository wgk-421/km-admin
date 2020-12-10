package com.km.main.admin.controller;

import com.km.main.admin.common.CommonResultInfo;
import com.km.main.admin.mbgen.mapper.UserMapper;
import com.km.main.admin.mbgen.model.Menu;
import com.km.main.admin.mbgen.model.User;
import com.km.main.admin.mbgen.model.UserExample;
import com.km.main.admin.service.IMenuService;
import com.km.main.admin.service.IUserAdminService;
import com.km.main.admin.vo.request.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 系统用户管理
 * @Author: GaoKunW
 * @Date: 2020/12/8 22:03
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    private IUserAdminService userAdminService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     *
     * @param userParam 用户入参
     * @return 返回token
     */
    @PostMapping("/login")
    public CommonResultInfo userLogin(@Validated @RequestBody UserParam userParam) {
        String result = userAdminService.login(userParam.getUsername(), userParam.getPassword());
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", result);
        tokenMap.put("tokenHead", "User-Auth-Token");
        return CommonResultInfo.success(tokenMap);
    }

    /**
     * 获取登录用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/info")
    public CommonResultInfo getUserInfo() {
        UserExample user = new UserExample();
        List<User> result =  userMapper.selectByExample(user);
        Map<String, Object> data = new HashMap<>();
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        data.put("username", result.get(0).getUserName());
        data.put("roles", roles);
        return CommonResultInfo.success(data);
    }
}
