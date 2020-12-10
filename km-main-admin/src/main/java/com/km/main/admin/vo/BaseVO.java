package com.km.main.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 基类
 * @Author: GaoKunW
 * @Date: 2020/12/9 19:45
 */
@Data
public class BaseVO {
    @ApiModelProperty(value = "1:有效 0:无效")
    private String validFlag;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建人(sys_user_t表 user_id字段)")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "最后更新人(sys_user_t表 user_id字段)")
    private String lastUpdateBy;

    @ApiModelProperty(value = "最后更新时间")
    private Date lastUpdateDate;
}
