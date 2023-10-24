package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dto.WhRegionDto;
import com.macro.mall.dto.WhResourceDto;
import com.macro.mall.dto.WhUserParamDto;
import com.macro.mall.mapper.WhPlatformsMapper;
import com.macro.mall.mapper.WhRegionsMapper;
import com.macro.mall.mapper.WhResourceItemsMapper;
import com.macro.mall.mapper.WhResourcesMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.WhResourceService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class WhResourceServiceImpl implements WhResourceService {
	@Autowired
	WhResourcesMapper whResourcesMapper;
	@Autowired
	WhResourceItemsMapper whResourceItemsMapper;
	@Autowired
	WhRegionsMapper whRegionsMapper;
	@Autowired
	WhPlatformsMapper whPlatformsMapper;
	
	@Override
	public CommonPage<? extends WhResources> list(Map<String, Object> searchMap) {
		if (Objects.isNull(searchMap)) {
			Asserts.fail("参数异常");
		}
		
		
		WhResourcesExample whResourcesExample = new WhResourcesExample();
		WhResourcesExample.Criteria criteria = whResourcesExample.createCriteria();
		
		// 查询条件
		if (!Objects.isNull(searchMap) && !searchMap.isEmpty()) {
			if (searchMap.containsKey("id")) {
				criteria.andIdEqualTo(Long.valueOf(String.valueOf(searchMap.get("id"))));
			}
			if (searchMap.containsKey("title")) {
				criteria.andTitleLike("%" + searchMap.get("title") + "%");
			}
			if (searchMap.containsKey("region")) {
				criteria.andRegionLike("%" + searchMap.get("region") + "%");
			}
			if (searchMap.containsKey("platform")&&!String.valueOf(searchMap.get("platform")).isBlank()) {
				criteria.andPlatformLike("%" + searchMap.get("platform") + "%");
			}
			if (searchMap.containsKey("productPlatform")&&!String.valueOf(searchMap.get("productPlatform")).isBlank()) {
				criteria.andFunctionRightKey("find_in_set", "product_platform", (String) searchMap.get("productPlatform"));
			}
			if (searchMap.containsKey("status")&&!String.valueOf(searchMap.get("status")).isBlank()) {
				criteria.andStatusEqualTo((Integer) searchMap.get("status"));
			}
			if (searchMap.containsKey("priceStart") && searchMap.containsKey("priceEnd") && !String.valueOf(searchMap.get("priceStart")).isBlank() && !String.valueOf(searchMap.get("priceEnd")).isBlank()) {
				// 先查item 根据item 结果获取resourse id
				WhResourceItemsExample whResourceItemsExample = new WhResourceItemsExample();
				WhResourceItemsExample.Criteria criteriaItem = whResourceItemsExample.createCriteria();
				criteriaItem.andPriceBetween(BigDecimal.valueOf((Long.valueOf(String.valueOf(searchMap.get("priceStart"))))), BigDecimal.valueOf((Long.valueOf(String.valueOf(searchMap.get("priceEnd"))))));
				List<WhResourceItems> whResourceItems = whResourceItemsMapper.selectByExampleWithBLOBs(whResourceItemsExample);
				ArrayList<Long> ids = new ArrayList<>();
				if (!whResourceItems.isEmpty()) {
					whResourceItems.forEach(item -> {
						ids.add(item.getResourceId());
					});
				}
				if (ids.isEmpty()) {
					ids.add((long) -1);
				}
				criteria.andIdIn(ids);
				
			}
		}
		// 带入条件查询结果
		List<WhResources> reslutList = new ArrayList<>();
		// 分页参数设置
		PageHelper.startPage((Integer) searchMap.getOrDefault("pageNum", 1), (Integer) searchMap.getOrDefault("pageSize", 10));
		List<WhResources> resources = whResourcesMapper.selectByExampleWithBLOBs(whResourcesExample);
		PageInfo<WhResources> pageInfo = new PageInfo<>(resources);
		if (!resources.isEmpty()) {
			// 查询resource_item
			resources.forEach(resource -> {
				WhResourceDto whResourceDto = new WhResourceDto(resource);
				WhResourceItemsExample whResourceItemsExample = new WhResourceItemsExample();
				whResourceItemsExample.setOrderByClause("price");
				WhResourceItemsExample.Criteria criteria1 = whResourceItemsExample.createCriteria();
				criteria1.andResourceIdEqualTo(whResourceDto.getId());
				List<WhResourceItems> whResourceItems = whResourceItemsMapper.selectByExampleWithBLOBs(whResourceItemsExample);
				whResourceDto.setWhResourceItemsList(whResourceItems);
				reslutList.add(whResourceDto);
			});
			CommonPage<WhResources> whResourcesCommonPage = CommonPage.restPage(reslutList);
			whResourcesCommonPage.setPageNum(pageInfo.getPageNum());
			whResourcesCommonPage.setPageSize(pageInfo.getPageSize());
			whResourcesCommonPage.setTotalPage(pageInfo.getPages());
			whResourcesCommonPage.setTotal(pageInfo.getTotal());
			return whResourcesCommonPage;
		}
		return CommonPage.restPage(reslutList);
		
	}
	
	@Override
	public CommonPage<?> regionList() {
		WhRegionsExample whRegionsExample = new WhRegionsExample();
		List<WhRegions> whRegions = whRegionsMapper.selectByExampleWithBLOBs(whRegionsExample);
		List<WhRegionDto> list = new ArrayList<>();
		if (!Objects.isNull(whRegions) && !whRegions.isEmpty()) {
			whRegions.forEach(regain -> {
				WhRegionDto whRegionDto = new WhRegionDto(regain);
				WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
				WhPlatformsExample.Criteria criteria = whPlatformsExample.createCriteria();
				criteria.andRegionIdEqualTo(regain.getId());
				List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
				whRegionDto.setChildren(whPlatforms);
				list.add(whRegionDto);
			});
		}
		return CommonPage.restPage(list);
	}
}
