package com.km.demo.controller;

import com.km.common.response.CommonResultInfo;
import com.km.common.utils.captcha.CaptchaUtils;
import com.km.common.utils.uuid.IdUtils;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: admin
 *
 * @author GaoKunW
 * @date 2021/2/7 23:58
 */
@Api(tags = "admin")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public CommonResultInfo log() {
        return CommonResultInfo.success();
    }

    @ApiOperation(value = "获取验证码")
    @GetMapping("/code")
    public CommonResultInfo getCode(){
        String img = CaptchaUtils.getCode("char");
        Map<String, String> map = new HashMap<>();
        map.put("img", img);
        map.put("uuid", IdUtils.simpleUUID());
      return CommonResultInfo.buildSuccess(map);
    };


}
