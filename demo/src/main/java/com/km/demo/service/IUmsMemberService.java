package com.km.demo.service;

import com.km.common.response.CommonResultInfo;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/4
 */
public interface IUmsMemberService {
    /**
     * 生成验证码
     *
     * @param telephone
     * @return
     */
    CommonResultInfo generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     *
     * @param telephone
     * @param authCode
     * @return
     */
    CommonResultInfo verifyAuthCode(String telephone, String authCode);
}
