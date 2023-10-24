package com.macro.mall.controller;

import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.WhCategoryParamDto;
import com.macro.mall.dto.WhUserParamDto;
import com.macro.mall.service.WhUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class WhUserController {
    @Autowired
    private WhUserService whUserService;

    @ApiOperation("创建网红")
    @ResponseBody
    @RequestMapping(value = "/wh/user", method = RequestMethod.POST)
    public CommonResult create(@RequestBody WhUserParamDto whUserParamDto) {
        whUserService.create(whUserParamDto);
        return CommonResult.success(whUserParamDto);
    }

    @ApiOperation("删除记录")
    @ResponseBody
    @RequestMapping(value = "/wh/user/{id}", method = RequestMethod.DELETE)
    public CommonResult del(@PathVariable("id") Long id) {
        Integer del = whUserService.del(id);
        if (del > 0) {
            return CommonResult.success("");
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除记录")
    @ResponseBody
    @RequestMapping(value = "/wh/user/{id}", method = RequestMethod.PUT)
    public CommonResult update(@PathVariable("id") Long id, @RequestBody WhUserParamDto whUserParamDto) {
        whUserParamDto.setId(id);
        Integer del = whUserService.update(whUserParamDto);
        if (del > 0) {
            return CommonResult.success("");
        }
        return CommonResult.failed();
    }

    @ApiOperation("查询记录")
    @ResponseBody
    @RequestMapping(value = "/wh/user", method = RequestMethod.GET)
    public CommonResult list(@RequestParam Map<String, Object> queryParams,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        queryParams.put("pageSize", pageSize);
        queryParams.put("pageNum", pageNum);
        return CommonResult.success(whUserService.list(queryParams) );
    }


}
