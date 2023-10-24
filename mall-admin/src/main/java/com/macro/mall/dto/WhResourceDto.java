package com.macro.mall.dto;

import cn.hutool.core.bean.BeanUtil;
import com.macro.mall.model.WhResourceItems;
import com.macro.mall.model.WhResources;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class WhResourceDto extends WhResources {
    Long lowPrice;
    List<WhResourceItems> whResourceItemsList;

    public WhResourceDto(WhResources whResources) {
        BeanUtil.copyProperties(whResources, this);
    }
}
