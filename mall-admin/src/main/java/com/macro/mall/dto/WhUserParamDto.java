package com.macro.mall.dto;

import cn.hutool.core.bean.BeanUtil;
import com.macro.mall.model.WhCategories;
import com.macro.mall.model.WhUserFans;
import com.macro.mall.model.WhUserVideos;
import com.macro.mall.model.WhUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhUserParamDto extends WhUsers {
    private List<WhCategories> cateGoryList;
    private List<WhUserFans> fansList;
    private List<WhUserVideos> userVideos;

    public WhUserParamDto(WhUsers whUsers) {
        BeanUtil.copyProperties(whUsers, this);
    }
}
