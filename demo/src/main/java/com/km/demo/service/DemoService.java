package com.km.demo.service;

import com.km.common.generate.mapper.MenuMapper;
import com.km.common.generate.model.Menu;
import com.km.common.generate.model.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/3
 */
@Service
public class DemoService implements IDemoService{
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findList(MenuExample menuExample) {
        return menuMapper.selectByExample(menuExample);
    }
}
