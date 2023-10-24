package com.macro.mall.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class WhResources implements Serializable {
    private Long id;

    private String serial;

    private String title;

    private String link;

    private Long platformId;

    private String region;

    private String platform;

    private String productPlatform;

    private Long members;

    private BigDecimal dayPush;

    private String tags;

    @ApiModelProperty(value = "0 正常 -1 删除")
    private Integer status;

    private Date createdTime;

    private Date updatedTime;

    private String headImageUrl;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getProductPlatform() {
        return productPlatform;
    }

    public void setProductPlatform(String productPlatform) {
        this.productPlatform = productPlatform;
    }

    public Long getMembers() {
        return members;
    }

    public void setMembers(Long members) {
        this.members = members;
    }

    public BigDecimal getDayPush() {
        return dayPush;
    }

    public void setDayPush(BigDecimal dayPush) {
        this.dayPush = dayPush;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serial=").append(serial);
        sb.append(", title=").append(title);
        sb.append(", link=").append(link);
        sb.append(", platformId=").append(platformId);
        sb.append(", region=").append(region);
        sb.append(", platform=").append(platform);
        sb.append(", productPlatform=").append(productPlatform);
        sb.append(", members=").append(members);
        sb.append(", dayPush=").append(dayPush);
        sb.append(", tags=").append(tags);
        sb.append(", status=").append(status);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", headImageUrl=").append(headImageUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}