package com.macro.mall.dto;

import cn.hutool.core.bean.BeanUtil;
import com.macro.mall.model.WhPlatforms;
import com.macro.mall.model.WhResourceItems;
import com.macro.mall.model.WhResources;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WhPlatformDto extends WhPlatforms {
	public WhPlatformDto(WhPlatforms whPlatforms) {
		BeanUtil.copyProperties(whPlatforms, this);
	}
}
