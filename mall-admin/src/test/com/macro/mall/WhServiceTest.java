package com.macro.mall;

import cn.hutool.json.JSONUtil;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.WhCategories;
import com.macro.mall.service.WhCategoryService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WhServiceTest {
    @Autowired
    private WhCategoryService whCategoryService;

    @Test
    public void test() {
        List<WhCategories> list = whCategoryService.list(null);
        System.out.println(JSONUtil.toJsonStr(list));
    }
}
