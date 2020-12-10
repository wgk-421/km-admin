package com.km.main.admin.dao;

import com.km.main.admin.mbgen.model.Menu;

import java.util.List;

/**
 * @Description:
 * @Author: GaoKunW
 * @Date: 2020/12/9 19:59
 */
public interface IMenuDao {
    List<Menu> findMenuAllList();
}
