package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
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
	
	@ApiOperation("资源记录查询")
	@ResponseBody
	@RequestMapping(value = "/wh/region", method = RequestMethod.GET)
	public CommonResult getRegion() {
		return CommonResult.success(whResourceService.regionList());
	}
}
