package com.macro.mall.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.model.WhResources;

import java.util.Map;

public interface WhResourceService {
	// 用户列表
	CommonPage<? extends WhResources> list(Map<String, Object> queryParams);
	
	CommonPage<?> regionList();
}
