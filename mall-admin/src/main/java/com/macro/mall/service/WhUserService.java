package com.macro.mall.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.dto.WhUserParamDto;
import com.macro.mall.model.WhUsers;

import java.util.List;
import java.util.Map;

public interface WhUserService {
    //创建用户
    Integer create(WhUserParamDto whUserParamDto);

    //删除用户
    Integer del(Long userId);

    //更新用户
    Integer update(WhUserParamDto whUserParamDto);

    //用户列表
    CommonPage<? extends  WhUsers> list(Map<String, Object> queryParams);
}
