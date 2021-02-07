package com.km.common.utils.captcha;

import com.km.common.utils.string.StringUtils;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;

import java.util.Locale;
import java.util.UUID;

/**
 * Description:验证码工具类，可自定义验证码样式
 *
 * @author GaoKunW
 * @date 2021/2/7 22:34
 */
public class CaptchaUtils {

    private static final String GIF_TYPE = "gif";
    private static final String MATH_TYPE = "math";
    private static final String CHINESE_TYPE = "Chinese";
    private static final String CHAR_TYPE = "char";




    public static String getCode(String type) {
        String codeKey = "";
        String codeResult = "";
        if (StringUtils.equals(CHAR_TYPE, type)) {
            SpecCaptcha specCaptcha = new SpecCaptcha();
            specCaptcha.text().toLowerCase(Locale.ROOT);
            codeResult = specCaptcha.toBase64();
        } else if(StringUtils.equals(GIF_TYPE, type)) {
            GifCaptcha captcha = new GifCaptcha();
            captcha.text();
        }
        return codeResult;
    }

    public static void main(String[] args) {
        System.out.println(CaptchaUtils.getCode("char"));
        SpecCaptcha specCaptcha = new SpecCaptcha();
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        // 将key和base64返回给前端
        System.out.println(specCaptcha.toBase64());


    }
}
