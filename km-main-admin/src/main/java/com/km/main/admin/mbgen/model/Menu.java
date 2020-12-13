package com.km.main.admin.mbgen.model;

import com.km.main.admin.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class Menu extends BaseVO implements Serializable {
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "路由地址")
    private String routePath;

    @ApiModelProperty(value = "组件路径")
    private String componentPath;

    @ApiModelProperty(value = "菜单类型(C:目录 M:菜单 B:按钮)")
    private String menuType;

    @ApiModelProperty(value = "链接类型(0:外链接 1:系统内链接)")
    private Integer linkType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "允许的权限名称")
    private String permissionName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "前端是否显示(1:隐藏 0:不隐藏)")
    private Integer hidden;

    @ApiModelProperty(value = "keep-alive (1:缓存 0:不缓存)")
    private Integer isCache;

    @ApiModelProperty(value = "子菜单")
    private List<Menu> ChildList;
}