package com.macro.mall.dto;

import cn.hutool.core.bean.BeanUtil;
import com.macro.mall.model.WhPlatforms;
import com.macro.mall.model.WhRegions;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class WhRegionDto extends WhRegions {
	public List<WhPlatforms> children;
	
	public WhRegionDto(WhRegions whRegions) {
		BeanUtil.copyProperties(whRegions, this);
	}
}
