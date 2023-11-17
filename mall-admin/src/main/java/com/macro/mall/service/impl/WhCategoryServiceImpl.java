package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.mapper.WhCategoriesMapper;
import com.macro.mall.model.WhCategories;
import com.macro.mall.model.WhCategoriesExample;
import com.macro.mall.service.WhCategoryService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WhCategoryServiceImpl implements WhCategoryService {
    @Autowired
    private WhCategoriesMapper whCategoriesMapper;

    @Override
    public int create(WhCategories categories) {
        WhCategoriesExample whCategoriesExample = new WhCategoriesExample();
        whCategoriesExample.createCriteria().andTitleEqualTo(categories.getTitle());
        List<WhCategories> whCategories = whCategoriesMapper.selectByExample(whCategoriesExample);
        if (!whCategories.isEmpty()) {
            Asserts.fail("分类名称已经存在");
        }
        categories.setCreatedTime(new Date());
        categories.setUpdatedTime(new Date());
        return whCategoriesMapper.insert(categories);
    }

    @Override
    public int update(Long id, Map<String, Object> map) {
        WhCategories whCategories = new WhCategories();
        whCategories.setId(id);
        if (map.containsKey("status") && !String.valueOf(map.get("status")).isBlank()) {
            whCategories.setStatus(Integer.valueOf(String.valueOf(map.get("status"))));
        }
        if (map.containsKey("title") && !String.valueOf(map.get("title")).isBlank()) {
            whCategories.setTitle(String.valueOf(map.get("title")));
        }
        return whCategoriesMapper.updateByPrimaryKeySelective(whCategories);
    }

    @Override
    public int delete(List<Long> ids) {
        if (Objects.isNull(ids) || ids.isEmpty()) {
            Asserts.fail("提交的参数不存在");
        }
        WhCategoriesExample whCategoriesExample = new WhCategoriesExample();
        whCategoriesExample.createCriteria().andIdIn(ids);
        return whCategoriesMapper.deleteByExample(whCategoriesExample);
    }

    @Override
    public List<WhCategories> list() {
        return whCategoriesMapper.selectByExample(new WhCategoriesExample());
    }

    @Override
    public List<WhCategories> list(Map<String, Object> searchMap) {
        if (Objects.isNull(searchMap)) {
            Asserts.fail("参数异常");
        }
        //分页参数设置
        PageHelper.startPage((Integer) searchMap.getOrDefault("pageNum", 1), (Integer) searchMap.getOrDefault("pageSize", 10));
        WhCategoriesExample whCategoriesExample = new WhCategoriesExample();
        WhCategoriesExample.Criteria criteria = whCategoriesExample.createCriteria();
        //查询条件
        if (!Objects.isNull(searchMap) && !searchMap.isEmpty()) {
            if (searchMap.containsKey("id")&&!String.valueOf(searchMap.get("id")).isBlank()) {
               criteria.andIdEqualTo(Long.valueOf(String.valueOf(searchMap.get("id"))));
            }
            if (searchMap.containsKey("title")) {
                criteria.andTitleLike("%" + searchMap.get("title") + "%");
            }
            if (searchMap.containsKey("status")) {
                criteria.andStatusEqualTo(Integer.valueOf((String) searchMap.get("status")));

            }
        }
        whCategoriesExample.setOrderByClause("sort asc");
        List<WhCategories> whCategories = whCategoriesMapper.selectByExample(whCategoriesExample);
        if (Objects.isNull(whCategories) || whCategories.isEmpty()) {
            return new ArrayList<>();
        }
        return whCategories;
    }
}
