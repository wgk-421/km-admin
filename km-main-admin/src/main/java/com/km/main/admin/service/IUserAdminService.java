package com.km.main.admin.service;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/8 23:23
 */
public interface IUserAdminService {
    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username,String password);
}
