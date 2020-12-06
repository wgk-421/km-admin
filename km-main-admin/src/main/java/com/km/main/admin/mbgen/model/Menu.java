package com.km.main.admin.mbgen.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Menu implements Serializable {
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    @ApiModelProperty(value = "父菜单ID")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "路由地址")
    private String url;

    @ApiModelProperty(value = "组件路径")
    private String componentPath;

    @ApiModelProperty(value = "菜单类型(C:目录 M:菜单 B:按钮)")
    private String menuType;

    @ApiModelProperty(value = "链接类型(0:外链接 1:系统内链接)")
    private String linkType;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "允许的权限名称")
    private String permissionName;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "1:有效 0:无效")
    private String invalidFlag;

    @ApiModelProperty(value = "前端是否显示(1:隐藏 0:不隐藏)")
    private String hidden;

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

    private static final long serialVersionUID = 1L;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInvalidFlag() {
        return invalidFlag;
    }

    public void setInvalidFlag(String invalidFlag) {
        this.invalidFlag = invalidFlag;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", menuId=").append(menuId);
        sb.append(", parentId=").append(parentId);
        sb.append(", menuName=").append(menuName);
        sb.append(", url=").append(url);
        sb.append(", componentPath=").append(componentPath);
        sb.append(", menuType=").append(menuType);
        sb.append(", linkType=").append(linkType);
        sb.append(", sort=").append(sort);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", icon=").append(icon);
        sb.append(", invalidFlag=").append(invalidFlag);
        sb.append(", hidden=").append(hidden);
        sb.append(", remark=").append(remark);
        sb.append(", createBy=").append(createBy);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateBy=").append(lastUpdateBy);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}