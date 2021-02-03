package com.km.demo.service;

import com.km.common.generate.model.Menu;
import com.km.common.generate.model.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 *
 * @Author GaoKunW
 * @Date 2021/2/3
 */
public interface IDemoService {
    List<Menu> findList(MenuExample menuExample);
}
