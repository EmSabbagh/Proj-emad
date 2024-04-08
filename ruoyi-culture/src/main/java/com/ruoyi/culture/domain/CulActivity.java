package com.ruoyi.culture.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动对象 cul_activity
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public class CulActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String name;

    /** 发布内容 */
    @Excel(name = "发布内容")
    private String content;

    /** 活动地点 */
    @Excel(name = "活动地点")
    private String place;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date activityTime;

    /** 发布人 */
    @Excel(name = "发布人")
    private Long publishUser;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setPlace(String place) 
    {
        this.place = place;
    }

    public String getPlace() 
    {
        return place;
    }
    public void setActivityTime(Date activityTime) 
    {
        this.activityTime = activityTime;
    }

    public Date getActivityTime() 
    {
        return activityTime;
    }
    public void setPublishUser(Long publishUser) 
    {
        this.publishUser = publishUser;
    }

    public Long getPublishUser() 
    {
        return publishUser;
    }
    public void setPublishTime(Date publishTime) 
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime() 
    {
        return publishTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("content", getContent())
            .append("place", getPlace())
            .append("activityTime", getActivityTime())
            .append("publishUser", getPublishUser())
            .append("publishTime", getPublishTime())
            .toString();
    }
}
