package com.km.main.admin.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @Description: 用户请求参数
 * @Author: GaoKunW
 * @Date: 2020/12/8 23:19
 */
@Data
@EqualsAndHashCode
public class UserParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "密码",required = true)
    private String password;
}
