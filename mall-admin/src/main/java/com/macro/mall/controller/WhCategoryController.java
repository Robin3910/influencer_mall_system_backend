package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.WhCategoryParamDto;
import com.macro.mall.model.WhCategories;
import com.macro.mall.service.WhCategoryService;
import com.macro.mall.service.impl.WhCategoryServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@Api(tags = "WhCategoryController")
@Tag(name = "WhCategoryController", description = "网红分类管理")
public class WhCategoryController {
    @Autowired
    private WhCategoryServiceImpl whCategoryService;

    @ApiOperation("添加分类")
    @RequestMapping(value = "/wh/add_category", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult add(@RequestBody WhCategoryParamDto whCategoryParamDto) {
        WhCategories whCategories = new WhCategories();
        whCategories.setTitle(whCategoryParamDto.getTitle());
        whCategories.setStatus(whCategoryParamDto.getStatus());
        whCategoryService.create(whCategories);
        return CommonResult.success(whCategories);
    }

    @ApiOperation("获取所有的分类")
    @RequestMapping(value = "/wh/get_category_list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<WhCategories>> getList(@RequestParam Map<String, Object> queryParams,
                                                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum
    ) {
        queryParams.put("pageSize", pageSize);
        queryParams.put("pageNum", pageNum);
        return CommonResult.success(CommonPage.restPage(whCategoryService.list(queryParams)));
    }


    @ApiOperation("删除分类")
    @RequestMapping(value = "/wh/del_category/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonResult del(@PathVariable Long id) {
        List<Long> arrayList = new ArrayList<>();
        arrayList.add(id);
        int count = whCategoryService.delete(arrayList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("编辑分类")
    @RequestMapping(value = "/wh/update_category/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,@RequestBody Map<String,Object> map) {

        int count = whCategoryService.update(id,map);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}
