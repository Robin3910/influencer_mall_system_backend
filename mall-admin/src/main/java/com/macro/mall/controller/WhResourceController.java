package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.WhPlatformDto;
import com.macro.mall.dto.WhResourceDto;
import com.macro.mall.service.WhResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WhResourceController {
	@Autowired
	WhResourceService whResourceService;
	
	@ApiOperation("资源记录查询")
	@ResponseBody
	@RequestMapping(value = "/wh/resource", method = RequestMethod.GET)
	public CommonResult list(@RequestParam Map<String, Object> queryParams,
	                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
	                         @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		queryParams.put("pageSize", pageSize);
		queryParams.put("pageNum", pageNum);
		return CommonResult.success(whResourceService.list(queryParams));
	}
	
	@ApiOperation("资源记录更新")
	@ResponseBody
	@RequestMapping(value = "/wh/resource/{id}", method = RequestMethod.PUT)
	public CommonResult updateResource(@PathVariable("id") Long id, @RequestBody WhResourceDto whResourceDto) {
		return CommonResult.success(whResourceService.updateResource(id, whResourceDto));
	}
	
	@ApiOperation("资源记录删除")
	@ResponseBody
	@RequestMapping(value = "/wh/resource/{id}", method = RequestMethod.DELETE)
	public CommonResult updateResource(@PathVariable("id") Long id) {
		return CommonResult.success(whResourceService.deleteResource(id));
	}
	
	@ApiOperation("资源记录创建")
	@ResponseBody
	@RequestMapping(value = "/wh/resource", method = RequestMethod.POST)
	public CommonResult createResource(@RequestBody WhResourceDto whResourceDto) {
		return CommonResult.success(whResourceService.createResource(whResourceDto));
	}
	
	
	@ApiOperation("国家列表")
	@ResponseBody
	@RequestMapping(value = "/wh/region", method = RequestMethod.GET)
	public CommonResult getRegion(@RequestParam Map<String, Object> queryParams) {
		return CommonResult.success(whResourceService.regionList(queryParams));
	}
	
	@ApiOperation("更新状态")
	@ResponseBody
	@RequestMapping(path = {
			"/wh/region/{id}",
			"/wh/update_region/{id}"
	}, method = RequestMethod.PUT)
	public CommonResult updateRegion(@PathVariable("id") Long id, @RequestBody Map<String, Object> map) {
		return CommonResult.success(whResourceService.updateRegion(id, map));
	}
	
	@ApiOperation("删除状态")
	@ResponseBody
	@RequestMapping(value = "/wh/region_delete/{id}", method = RequestMethod.DELETE)
	public CommonResult deleteRegion(@PathVariable("id") Long id) {
		return CommonResult.success(whResourceService.deleteRegion(id));
	}
	
	@ApiOperation("平台创建")
	@ResponseBody
	@RequestMapping(value = "/wh/platform", method = RequestMethod.POST)
	public CommonResult createResource(@RequestBody WhPlatformDto whPlatformDto) {
		return CommonResult.success(whResourceService.createPlatForm(whPlatformDto));
	}
	
	@ApiOperation("平台列表")
	@ResponseBody
	@RequestMapping(value = "/wh/platform", method = RequestMethod.GET)
	public CommonResult getPlatform(@RequestParam Map<String, Object> queryParams) {
		return CommonResult.success(whResourceService.platformList(queryParams));
	}
	
	@ApiOperation("更新平台状态")
	@ResponseBody
	@RequestMapping(value = "/wh/platform/{id}", method = RequestMethod.PUT)
	public CommonResult updatePlatform(@PathVariable("id") Long id, @RequestBody Map<String, Object> map) {
		return CommonResult.success(whResourceService.updatePlatform(id, map));
	}
}
