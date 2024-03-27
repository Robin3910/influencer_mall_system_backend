package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dto.WhPlatformDto;
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
import org.springframework.transaction.annotation.Transactional;

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
			if (searchMap.containsKey("platform") && !String.valueOf(searchMap.get("platform")).isBlank()) {
				criteria.andPlatformLike("%" + searchMap.get("platform") + "%");
			}
			if (searchMap.containsKey("productPlatform") && !String.valueOf(searchMap.get("productPlatform")).isBlank()) {
				criteria.andProductPlatformLike("%" + searchMap.get("productPlatform") + "%");
			}
			if (searchMap.containsKey("status") && !String.valueOf(searchMap.get("status")).isBlank()) {
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
				if (!whResourceItems.isEmpty()) {
					whResourceDto.setWhResourceItemsList(whResourceItems);
					whResourceDto.setLowPrice((new BigDecimal(String.valueOf(whResourceItems.get(0).getPrice())).longValue()));
				}
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
	public CommonPage<?> regionList(Map<String, Object> queryParams) {
		WhRegionsExample whRegionsExample = new WhRegionsExample();
		WhRegionsExample.Criteria criteria1 = whRegionsExample.createCriteria();
		// 分页参数设置
		PageHelper.startPage(Integer.valueOf(String.valueOf(queryParams.getOrDefault("pageNum", 1))), Integer.valueOf(String.valueOf(queryParams.getOrDefault("pageSize", 10))));
		
		if (queryParams.containsKey("id") && !String.valueOf(queryParams.get("id")).isBlank()) {
			criteria1.andIdEqualTo(Long.valueOf((String) queryParams.get("id")));
		}
		if (queryParams.containsKey("name") && !String.valueOf(queryParams.get("name")).isBlank()) {
			criteria1.andNameLike("%" + queryParams.get("name") + "%");
		}
		if (queryParams.containsKey("status") && !String.valueOf(queryParams.get("status")).isBlank()) {
			criteria1.andStatusEqualTo(Integer.valueOf(String.valueOf(queryParams.get("status"))));
		}
		
		
		whRegionsExample.setOrderByClause("sort asc");
		List<WhRegions> whRegions = whRegionsMapper.selectByExampleWithBLOBs(whRegionsExample);
		List<WhRegionDto> list = new ArrayList<>();
		if (!Objects.isNull(whRegions) && !whRegions.isEmpty()) {
			whRegions.forEach(regain -> {
				WhRegionDto whRegionDto = new WhRegionDto(regain);
				WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
				WhPlatformsExample.Criteria criteria = whPlatformsExample.createCriteria();
				criteria.andRegionIdEqualTo(regain.getId());
				// 检查状态是否包含在1和0之间
				if (queryParams.containsKey("platformStatus") && !String.valueOf(queryParams.get("platformStatus")).isBlank()) {
					criteria.andStatusEqualTo(Short.valueOf(String.valueOf(queryParams.get("platformStatus"))));
				}
				whPlatformsExample.setOrderByClause("sort asc");
				List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
				whRegionDto.setChildren(whPlatforms);
				list.add(whRegionDto);
			});
		}
		return CommonPage.restPage(list);
	}
	
	@Override
	public int updateRegion(Long id, Map<String, Object> map) {
		WhRegions whRegions = new WhRegions();
		whRegions.setId(id);
		if (map.containsKey("status") && !String.valueOf(map.get("status")).isBlank()) {
			whRegions.setStatus(Integer.valueOf(String.valueOf(map.get("status"))));
		}
		if (map.containsKey("name") && !String.valueOf(map.get("name")).isBlank()) {
			whRegions.setName(String.valueOf(map.get("name")));
		}
		if (map.containsKey("sort") && !String.valueOf(map.get("sort")).isBlank()) {
			whRegions.setSort(Integer.valueOf(String.valueOf(map.get("sort"))));
		}
		System.out.println(whRegions);
		return whRegionsMapper.updateByPrimaryKeySelective(whRegions);
	}
	
	@Override
	public int deleteRegion(Long id) {
		return whRegionsMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	@Transactional
	public int updateResource(Long id, WhResourceDto whResourceDto) {
		// 更新数据
		whResourcesMapper.updateByPrimaryKeySelective(whResourceDto);
		// 根据国家名称查询
		WhRegionsExample whRegionsExample = new WhRegionsExample();
		whRegionsExample.createCriteria().andNameEqualTo(whResourceDto.getRegion());
		List<WhRegions> whRegions = whRegionsMapper.selectByExample(whRegionsExample);
		if (!whRegions.isEmpty()) {
			// 	根据国家id以及平台名查询关联记录 存在 插入关联记录id  不存在  新建记录
			WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
			whPlatformsExample.createCriteria().andRegionIdEqualTo(whRegions.get(0).getId()).andNameEqualTo(whResourceDto.getPlatform());
			List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
			if (whPlatforms.size() == 1) {
				whResourceDto.setPlatformId(whPlatforms.get(0).getId());
			}
			if (whPlatforms.isEmpty()) {
				WhPlatforms whPlatforms1 = new WhPlatforms();
				whPlatforms1.setName(whResourceDto.getPlatform());
				whPlatforms1.setRegionId(whRegions.get(0).getId());
				whPlatforms1.setStatus((short) 0);
				whPlatforms1.setCreatedTime(new Date());
				whPlatforms1.setUpdatedTime(new Date());
				whPlatformsMapper.insert(whPlatforms1);
				whResourceDto.setPlatformId(whPlatforms1.getId());
			}
		}
		// 删除item
		WhResourceItemsExample whResourceItemsExample = new WhResourceItemsExample();
		whResourceItemsExample.createCriteria().andResourceIdEqualTo(whResourceDto.getId());
		whResourceItemsMapper.deleteByExample(whResourceItemsExample);
		// 重新插入
		if (!Objects.isNull(whResourceDto.getWhResourceItemsList()) && !whResourceDto.getWhResourceItemsList().isEmpty()) {
			whResourceDto.getWhResourceItemsList().forEach(whResourceItems -> {
				whResourceItems.setResourceId(whResourceDto.getId());
				whResourceItems.setCreatedTime(new Date());
				whResourceItems.setUpdatedTime(new Date());
				whResourceItemsMapper.insert(whResourceItems);
			});
		}
		return 1;
	}
	
	@Override
	@Transactional
	public int deleteResource(Long id) {
		whResourcesMapper.deleteByPrimaryKey(id);
		WhResourceItemsExample whResourceItemsExample = new WhResourceItemsExample();
		whResourceItemsExample.createCriteria().andResourceIdEqualTo(id);
		whResourceItemsMapper.deleteByExample(whResourceItemsExample);
		return 0;
	}
	
	@Override
	public WhResourceDto createResource(WhResourceDto whResourceDto) {
		whResourceDto.setCreatedTime(new Date());
		whResourceDto.setUpdatedTime(new Date());
		// 根据国家名称查询
		WhRegionsExample whRegionsExample = new WhRegionsExample();
		whRegionsExample.createCriteria().andNameEqualTo(whResourceDto.getRegion());
		List<WhRegions> whRegions = whRegionsMapper.selectByExample(whRegionsExample);
		if (!whRegions.isEmpty()) {
			// 	根据国家id以及平台名查询关联记录 存在 插入关联记录id  不存在  新建记录
			WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
			whPlatformsExample.createCriteria().andRegionIdEqualTo(whRegions.get(0).getId()).andNameEqualTo(whResourceDto.getPlatform());
			List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
			if (whPlatforms.size() == 1) {
				whResourceDto.setPlatformId(whPlatforms.get(0).getId());
			}
			if (whPlatforms.isEmpty()) {
				WhPlatforms whPlatforms1 = new WhPlatforms();
				whPlatforms1.setName(whResourceDto.getPlatform());
				whPlatforms1.setRegionId(whRegions.get(0).getId());
				whPlatforms1.setStatus((short) 0);
				whPlatforms1.setCreatedTime(new Date());
				whPlatforms1.setUpdatedTime(new Date());
				whPlatformsMapper.insert(whPlatforms1);
				whResourceDto.setPlatformId(whPlatforms1.getId());
			}
			
			
		}
		whResourcesMapper.insert(whResourceDto);
		if (!whResourceDto.getWhResourceItemsList().isEmpty()) {
			whResourceDto.getWhResourceItemsList().forEach(whResourceItems -> {
				whResourceItems.setResourceId(whResourceDto.getId());
				whResourceItems.setCreatedTime(new Date());
				whResourceItems.setUpdatedTime(new Date());
				whResourceItemsMapper.insert(whResourceItems);
			});
		}
		return whResourceDto;
	}
	
	@Override
	public CommonPage<?> platformList(Map<String, Object> queryParams) {
		WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
		WhPlatformsExample.Criteria criteria = whPlatformsExample.createCriteria();
		// 分页参数设置
		PageHelper.startPage(Integer.valueOf(String.valueOf(queryParams.getOrDefault("pageNum", 1))), Integer.valueOf(String.valueOf(queryParams.getOrDefault("pageSize", 10))));
		if (queryParams.containsKey("id") && !String.valueOf(queryParams.get("id")).isBlank()) {
			criteria.andIdEqualTo(Long.valueOf((String) queryParams.get("id")));
		}
		if (queryParams.containsKey("regionId") && !String.valueOf(queryParams.get("regionId")).isBlank()) {
			criteria.andRegionIdEqualTo(Long.valueOf((String) queryParams.get("regionId")));
		}
		if (queryParams.containsKey("name") && !String.valueOf(queryParams.get("name")).isBlank()) {
			criteria.andNameLike("%" + queryParams.get("name") + "%");
		}
		if (queryParams.containsKey("status") && !String.valueOf(queryParams.get("status")).isBlank()) {
			criteria.andStatusEqualTo(Short.valueOf(String.valueOf(queryParams.get("status"))));
		}
		whPlatformsExample.setOrderByClause("sort asc");
		List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
		return CommonPage.restPage(whPlatforms);
	}
	
	// 更新平台状态
	@Override
	public int updatePlatform(Long id, Map<String, Object> map) {
		WhPlatforms whPlatforms = new WhPlatforms();
		whPlatforms.setId(id);
		if (map.containsKey("status") && !String.valueOf(map.get("status")).isBlank()) {
			whPlatforms.setStatus(Short.valueOf(String.valueOf(map.get("status"))));
		}
		if (map.containsKey("sort") && !String.valueOf(map.get("sort")).isBlank()) {
			whPlatforms.setSort(Integer.valueOf(String.valueOf(map.get("sort"))));
		}
		return whPlatformsMapper.updateByPrimaryKeySelective(whPlatforms);
	}
	
	@Override
	public int createPlatForm(WhPlatformDto whPlatformDto) {
		WhPlatformsExample whPlatformsExample = new WhPlatformsExample();
		whPlatformsExample.createCriteria().andNameEqualTo(whPlatformDto.getName()).andRegionIdEqualTo(whPlatformDto.getRegionId());
		List<WhPlatforms> whPlatforms = whPlatformsMapper.selectByExample(whPlatformsExample);
		if (whPlatforms.isEmpty()) {
			whPlatformDto.setCreatedTime(new Date());
			whPlatformDto.setUpdatedTime(new Date());
			return whPlatformsMapper.insert(whPlatformDto);
		}
		return 0;
	}
}
