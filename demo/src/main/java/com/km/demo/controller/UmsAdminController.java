package com.km.demo.controller;

import com.km.common.generate.model.UmsAdmin;
import com.km.common.response.CommonResultInfo;
import com.km.demo.service.IUmsAdminService;
import com.km.demo.vo.request.UmsAdminLoginParam;
import com.km.demo.vo.request.UmsAdminParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/5
 */
@RestController
@Api(tags = "后台用户管理")
@RequestMapping("/admin/user")
public class UmsAdminController {
    @Autowired
    private IUmsAdminService adminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public CommonResultInfo<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResultInfo.failed();
        }
        return CommonResultInfo.buildSuccess(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @PostMapping(value = "/login")
    public CommonResultInfo login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResultInfo.buildFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResultInfo.buildSuccess(tokenMap);
    }

//    @ApiOperation("获取用户所有权限（包括+-权限）")
//    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResultInfo<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
//        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
//        return CommonResultInfo.success(permissionList);
//    }
}
