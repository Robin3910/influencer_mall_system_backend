package com.macro.mall.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.exception.Asserts;
import com.macro.mall.dto.WhUserParamDto;
import com.macro.mall.mapper.WhCategoriesMapper;
import com.macro.mall.mapper.WhUserFansMapper;
import com.macro.mall.mapper.WhUserVideosMapper;
import com.macro.mall.mapper.WhUsersMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.WhUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WhUserServiceImpl implements WhUserService {
	@Autowired
	private WhUsersMapper usersMapper;
	
	@Autowired
	private WhCategoriesMapper whCategoriesMapper;
	@Autowired
	private WhUserVideosMapper userVideosMapper;
	
	@Autowired
	private WhUserFansMapper whUserFansMapper;
	
	
	@Override
	@Transactional
	public Integer create(WhUserParamDto whUserParamDto) {
		// 插数据
		whUserParamDto.setStatus(0);
		whUserParamDto.setCreatedTime(new Date());
		whUserParamDto.setUpdatedTime(new Date());
		usersMapper.insertSelective(whUserParamDto);
		whUserParamDto.setNo(String.format("%1$8s", whUserParamDto.getId()).replace(' ', '0'));
		usersMapper.updateByPrimaryKey(whUserParamDto);
		if (!Objects.isNull(whUserParamDto.getUserVideos()) && !whUserParamDto.getUserVideos().isEmpty()) {
			whUserParamDto.getUserVideos().forEach(videos -> {
				videos.setUserId(whUserParamDto.getId());
				videos.setCreatedTime(new Date());
				videos.setUpdatedTime(new Date());
				userVideosMapper.insert(videos);
			});
		}
		if (!Objects.isNull(whUserParamDto.getFansList()) && !whUserParamDto.getFansList().isEmpty()) {
			whUserParamDto.getFansList().forEach(fans -> {
			 fans.setUserId(whUserParamDto.getId());
				fans.setCreatedTime(new Date());
				fans.setUpdatedTime(new Date());
				whUserFansMapper.insert(fans);
			});
		}
		return 1;
	}
	
	@Override
	public Integer del(Long userId) {
		WhUsers whUsers = new WhUsers();
		whUsers.setId(userId);
		return usersMapper.deleteByPrimaryKey(userId);
	}
	
	@Override
	public Integer update(WhUserParamDto whUserParamDto) {
		// 更新网红列表
		whUserParamDto.setUpdatedTime(new Date());
		usersMapper.updateByPrimaryKeySelective(whUserParamDto);
		// 清空历史关联数据
		WhUserVideosExample whUserVideosExample = new WhUserVideosExample();
		whUserVideosExample.createCriteria().andUserIdEqualTo(whUserParamDto.getId());
		userVideosMapper.deleteByExample(whUserVideosExample);
		if (!Objects.isNull(whUserParamDto.getUserVideos()) && !whUserParamDto.getUserVideos().isEmpty()) {
			whUserParamDto.getUserVideos().forEach(videos -> {
				videos.setUserId(whUserParamDto.getId());
				videos.setCreatedTime(new Date());
				videos.setUpdatedTime(new Date());
				userVideosMapper.insert(videos);
			});
		}
		// 清空粉丝数据
		WhUserFansExample whUserFansExample=new WhUserFansExample();
		whUserFansExample.createCriteria().andUserIdEqualTo(whUserParamDto.getId());
		whUserFansMapper.deleteByExample(whUserFansExample);
		if (!Objects.isNull(whUserParamDto.getFansList()) && !whUserParamDto.getFansList().isEmpty()) {
			whUserParamDto.getFansList().forEach(videos -> {
				videos.setUserId(whUserParamDto.getId());
				videos.setCount(videos.getCount());
				videos.setPlatform(videos.getPlatform());
				videos.setUrl(videos.getUrl());
				videos.setCreatedTime(new Date());
				videos.setUpdatedTime(new Date());
				whUserFansMapper.insert(videos);
			});
		}
		return 1;
		
	}
	
	@Override
	public CommonPage<WhUserParamDto> list(Map<String, Object> searchMap) {
		if (Objects.isNull(searchMap)) {
			Asserts.fail("参数异常");
		}
		
		WhUsersExample whUsersExample = new WhUsersExample();
		WhUsersExample.Criteria criteria = whUsersExample.createCriteria();
		// 查询条件
		if (!Objects.isNull(searchMap) && !searchMap.isEmpty()) {
			if (searchMap.containsKey("id")) {
				criteria.andIdEqualTo(Long.valueOf(String.valueOf(searchMap.get("id"))));
			}
			if (searchMap.containsKey("name")) {
				criteria.andNameLike("%" + searchMap.get("name") + "%");
			}
			if (searchMap.containsKey("no") && !String.valueOf(searchMap.get("no")).isBlank()) {
				criteria.andNoLike("%" + searchMap.get("no") + "%");
			}
			if (searchMap.containsKey("status")) {
				criteria.andStatusEqualTo((Integer) searchMap.get("status"));
			}
			if (searchMap.containsKey("categoryId") && !String.valueOf(searchMap.get("categoryId")).isBlank()) {
				criteria.andFunctionRightKey("find_in_set", "category_id", (String) searchMap.get("categoryId"));
			}
			if (searchMap.containsKey("regionId") && !String.valueOf(searchMap.get("regionId")).isBlank()) {
				criteria.andRegionIdEqualTo(Integer.valueOf(String.valueOf(searchMap.get("regionId"))));
			}
			if (searchMap.containsKey("videoType") && !String.valueOf(searchMap.get("videoType")).isBlank()) {
				criteria.andVideoTypeEqualTo(Short.valueOf(String.valueOf(searchMap.get("videoType"))));
			}
		}
		if (searchMap.containsKey("priceSort") && !String.valueOf(searchMap.get("priceSort")).isBlank()) {
			if(searchMap.get("priceSort").equals("asc")){
				whUsersExample.setOrderByClause("real_price asc");
			}else{
				whUsersExample.setOrderByClause("real_price desc");
			}
		}
		// 分页参数设置
		PageHelper.startPage((Integer) searchMap.getOrDefault("pageNum", 1), (Integer) searchMap.getOrDefault("pageSize", 10));
		List<WhUsers> whUsers = usersMapper.selectByExampleWithBLOBs(whUsersExample);
		PageInfo<WhUsers> pageInfo = new PageInfo(whUsers);
		
		if (!Objects.isNull(whUsers) && !whUsers.isEmpty()) {
			List<WhUserParamDto> whUserParamDtos = new ArrayList<>();
			whUsers.forEach(users -> {
				WhUserParamDto whUserParamDto = new WhUserParamDto(users);
				whUserParamDtos.add(whUserParamDto);
			});
			// 查询所有的关联地址数据
			whUserParamDtos.forEach(whUserParamDto -> {
				// 关联地址信息
				WhUserVideosExample whUserVideosExample = new WhUserVideosExample();
				whUserVideosExample.createCriteria().andUserIdEqualTo(whUserParamDto.getId());
				List<WhUserVideos> whUserVideos = userVideosMapper.selectByExampleWithBLOBs(whUserVideosExample);
				whUserParamDto.setUserVideos(whUserVideos);
				//  获取关联的粉丝列表
				WhUserFansExample whUserFansExample = new WhUserFansExample();
				whUserFansExample.createCriteria().andUserIdEqualTo(whUserParamDto.getId());
				List<WhUserFans> whUserFans = whUserFansMapper.selectByExampleWithBLOBs(whUserFansExample);
				whUserParamDto.setFansList(whUserFans);
				// 关联分类
				WhCategoriesExample whCategoriesExample = new WhCategoriesExample();
				String[] ids = whUserParamDto.getCategoryId().split(",");
				Long[] lids = new Long[ids.length];
				for (int i = 0; i < ids.length; i++) {
					lids[i] = Long.parseLong(ids[i]);
				}
				whCategoriesExample.createCriteria().andIdIn(Arrays.asList(lids));
				List<WhCategories> whCategories = whCategoriesMapper.selectByExample(whCategoriesExample);
				whUserParamDto.setCateGoryList(whCategories);
			});
			
			CommonPage<WhUserParamDto> whUserParamDtoCommonPage = CommonPage.restPage(whUserParamDtos);
			whUserParamDtoCommonPage.setPageNum(pageInfo.getPageNum());
			whUserParamDtoCommonPage.setPageSize(pageInfo.getPageSize());
			whUserParamDtoCommonPage.setTotalPage(pageInfo.getPages());
			whUserParamDtoCommonPage.setTotal(pageInfo.getTotal());
			return whUserParamDtoCommonPage;
		}
		return CommonPage.restPage(new ArrayList<>());
	}
}
