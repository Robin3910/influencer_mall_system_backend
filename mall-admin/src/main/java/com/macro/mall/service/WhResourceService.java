package com.macro.mall.service;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.dto.WhResourceDto;
import com.macro.mall.model.WhResources;

import java.util.Map;

public interface WhResourceService {
	// 用户列表
	CommonPage<? extends WhResources> list(Map<String, Object> queryParams);
	
	CommonPage<?> regionList( Map<String, Object> queryParams);
	int updateRegion(Long id, Map<String, Object> map);
	int deleteRegion(Long id);
	int updateResource(Long id,WhResourceDto whResourceDto);
	
	int deleteResource(Long id);
	
	WhResourceDto createResource(WhResourceDto whResourceDto);
	
	
}
