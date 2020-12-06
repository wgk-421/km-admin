drop database if exists `km`;
CREATE DATABASE `km` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

-- ----------------------------
-- Table structure for sys_menu_t
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_t`;
CREATE TABLE `km`.`sys_menu_t`
(
    `menu_id`          bigint(10)   NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `parent_id`        bigint(10)   NULL DEFAULT 0 COMMENT '父菜单ID',
    `menu_name`        varchar(100) NOT NULL COMMENT '菜单名称',
    `url`              varchar(255) NULL DEFAULT '' COMMENT '路由地址',
    `component_path`   varchar(255) NULL DEFAULT NULL COMMENT '组件路径',
    `menu_type`        varchar(2)   NULL DEFAULT '#' COMMENT '菜单类型(C:目录 M:菜单 B:按钮)',
    `link_type`        char(1)           DEFAULT 1 COMMENT '链接类型(0:外链接 1:系统内链接)',
    `sort`             int          NULL DEFAULT 0 COMMENT '排序',
    `permission_name`  varchar(255) NULL DEFAULT NULL COMMENT '允许的权限名称',
    `icon`             varchar(100) NULL DEFAULT '#' COMMENT '图标',
    `invalid_flag`     char(1)      NULL DEFAULT 1 COMMENT '1:有效 0:无效',
    `hidden`           char(1)      NULL DEFAULT 0 COMMENT '前端是否显示(1:隐藏 0:不隐藏)',
    `remark`           varchar(255) NOT NULL COMMENT '备注',
    `create_by`        varchar(20)  NULL DEFAULT '' COMMENT '创建人(sys_user_t表 user_id字段)',
    `create_date`      datetime     NULL COMMENT '创建时间',
    `last_update_by`   varchar(20)  NULL DEFAULT '' COMMENT '最后更新人(sys_user_t表 user_id字段)',
    `last_update_date` datetime     NULL COMMENT '最后更新时间',
    PRIMARY KEY (`menu_id`)
) ENGINE = InnoDB COMMENT = '菜单表';

-- ----------------------------
-- Records of sys_menu_t
-- ----------------------------
INSERT INTO `km`.`sys_menu_t` VALUES (1, 0, '系统管理', 'system', NULL, 'C', '1', 1, 'admin', '#', '1', '0', '系统菜单', '123456', '2020-12-06 21:18:02', '123456', '2020-12-06 21:18:10');
INSERT INTO `km`.`sys_menu_t` VALUES (2, 0, '业务数据配置', 'business', NULL, 'C', '1', 2, 'admin', '#', '1', '0', '业务数据配置', '123456', '2020-12-06 21:18:02', '123456', '2020-12-06 21:18:10');
INSERT INTO `km`.`sys_menu_t` VALUES (3, 0, '系统工具', 'tools', NULL, 'C', '1', 3, 'admin', '#', '1', '0', '系统工具', '123456', '2020-12-06 21:18:02', '123456', '2020-12-06 21:18:10');
INSERT INTO `km`.`sys_menu_t` VALUES (101, 1, '用户管理', 'user', 'system/user/index', 'M', '1', 1, 'system:user:list', 'user', '1', '0', '用户管理地址', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
INSERT INTO `km`.`sys_menu_t` VALUES (102, 1, '角色管理', 'role', 'system/role/index', 'M', '1', 2, 'system:role:list', 'role', '1', '0', '角色管理地址', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
INSERT INTO `km`.`sys_menu_t` VALUES (103, 1, '部门管理', 'dept', 'system/dept/index', 'M', '1', 3, 'system:dept:list', 'dept', '1', '0', '部门管理地址', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
INSERT INTO `km`.`sys_menu_t` VALUES (201, 2, '技能配置', 'skill', 'business/skill/index', 'M', '1', 1, 'business:skill:list', 'skill', '1', '0', '技能配置', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
INSERT INTO `km`.`sys_menu_t` VALUES (301, 3, '代码生成', 'gen', 'tools/gen/index', 'M', '1', 1, 'tools:gen:list', 'skill', '1', '0', '生成代码', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
INSERT INTO `km`.`sys_menu_t` VALUES (1001, 101, '用户查询', '', 'tools/gen/index', 'M', '1', 1, 'system:user:query', 'skill', '1', '0', '技能配置', '123456', '2020-12-06 21:26:43', '123456', '2020-12-06 21:26:47');
