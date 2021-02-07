package com.km.demo.service.impl;

import com.km.common.response.CommonResultInfo;
import com.km.common.utils.string.StringUtils;
import com.km.demo.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/4
 */
@Service
public class UmsMemberService implements IUmsMemberService {
    @Autowired
    private RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;
    /**
     * 生成验证码
     *
     * @param telephone
     * @return
     */
    @Override
    public CommonResultInfo generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResultInfo.buildSuccess(sb.toString(), "获取验证码成功");
    }

    /**
     * 判断验证码和手机号码是否匹配
     *
     * @param telephone
     * @param authCode
     * @return
     */
    @Override
    public CommonResultInfo verifyAuthCode(String telephone, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResultInfo.buildSuccess("请输入验证码");
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommonResultInfo.buildSuccess( "验证码校验成功");
        } else {
            return CommonResultInfo.buildSuccess("验证码不正确");
        }    }
}
