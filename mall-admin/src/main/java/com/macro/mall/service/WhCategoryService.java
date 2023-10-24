package com.macro.mall.service;

import com.macro.mall.model.UmsRole;
import com.macro.mall.model.WhCategories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface WhCategoryService {

    int create(WhCategories categories);

    int update(Long id, Map<String, Object> map);

    int delete(List<Long> ids);

    List<WhCategories> list();

    List<WhCategories> list(Map<String, Object> map);
}
