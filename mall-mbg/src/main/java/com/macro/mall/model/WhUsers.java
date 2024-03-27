package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WhUsers implements Serializable {
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "编号")
    private String no;

    @ApiModelProperty(value = "分类id")
    private String categoryId;

    @ApiModelProperty(value = "非会员价格")
    private BigDecimal price;

    @ApiModelProperty(value = "会员价格")
    private BigDecimal realPrice;

    private Date createdTime;

    @ApiModelProperty(value = "0 正常 -1删除")
    private Integer status;

    @ApiModelProperty(value = "可提供视频类型 0 默认 1影响者视频 2 影响者视频（原视频）3素人视频（原视频）")
    private Short videoType;

    @ApiModelProperty(value = "国家ID")
    private Integer regionId;

    private Date updatedTime;

    @ApiModelProperty(value = "是否显示 姓名和粉丝数0 正常 -1删除")
    private Integer isShowNameAndFans;

    @ApiModelProperty(value = "是否显示 姓名0 正常 -1删除")
    private Integer isShowName;

    @ApiModelProperty(value = "是否显示 粉丝数0 正常 -1删除")
    private Integer isShowFans;

    @ApiModelProperty(value = "头像")
    private String headImageUrl;

    private String descript;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Short getVideoType() {
        return videoType;
    }

    public void setVideoType(Short videoType) {
        this.videoType = videoType;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getIsShowNameAndFans() {
        return isShowNameAndFans;
    }

    public void setIsShowNameAndFans(Integer isShowNameAndFans) {
        this.isShowNameAndFans = isShowNameAndFans;
    }

    public Integer getIsShowName() {
        return isShowName;
    }

    public void setIsShowName(Integer isShowName) {
        this.isShowName = isShowName;
    }

    public Integer getIsShowFans() {
        return isShowFans;
    }

    public void setIsShowFans(Integer isShowFans) {
        this.isShowFans = isShowFans;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", no=").append(no);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", price=").append(price);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", status=").append(status);
        sb.append(", videoType=").append(videoType);
        sb.append(", regionId=").append(regionId);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", isShowNameAndFans=").append(isShowNameAndFans);
        sb.append(", isShowName=").append(isShowName);
        sb.append(", isShowFans=").append(isShowFans);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", descript=").append(descript);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}