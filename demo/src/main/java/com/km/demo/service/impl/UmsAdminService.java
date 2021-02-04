package com.km.demo.service.impl;

import com.km.common.generate.mapper.UmsAdminMapper;
import com.km.common.generate.model.UmsAdmin;
import com.km.common.generate.model.UmsAdminExample;
import com.km.common.generate.model.UmsResource;
import com.km.common.generate.model.UmsRole;
import com.km.demo.service.IUmsAdminService;
import com.km.demo.vo.request.UmsAdminParam;
import com.km.security.jwt.JwtTokenUtils;
import com.km.security.jwt.vo.AdminUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/5
 */
@Service
public class UmsAdminService implements IUmsAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminService.class);

    @Autowired
    private UmsAdminMapper adminMapper;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
//        UmsAdmin admin = adminCacheService.getAdmin(username);
//        if(admin!=null) return  admin;
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
//            admin = adminList.get(0);
//            adminCacheService.setAdmin(admin);
            return adminList.get(0);
        }
        return null;
    }

    /**
     * 注册功能
     *
     * @param umsAdminParam
     */
    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (umsAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                LOGGER.info("密码不正确");
            }
            if(!userDetails.isEnabled()){
                LOGGER.info("帐号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtils.getToken(userDetails);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的token
     */
    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    /**
     * 根据用户id获取用户
     *
     * @param id
     */
    @Override
    public UmsAdmin getItem(Long id) {
        return null;
    }

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     */
    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    /**
     * 修改指定用户信息
     *
     * @param id
     * @param admin
     */
    @Override
    public int update(Long id, UmsAdmin admin) {
        return 0;
    }

    /**
     * 删除指定用户
     *
     * @param id
     */
    @Override
    public int delete(Long id) {
        return 0;
    }

    /**
     * 修改用户角色关系
     *
     * @param adminId
     * @param roleIds
     */
    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        return 0;
    }

    /**
     * 获取用户对于角色
     *
     * @param adminId
     */
    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return null;
    }

    /**
     * 获取指定用户的可访问资源
     *
     * @param adminId
     */
    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return new ArrayList<>();
    }

    /**
     * 获取用户信息
     *
     * @param username
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        return null;
    }
}
