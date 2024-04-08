package com.ruoyi.culture.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 外链管理对象 cul_backlinks
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public class CulBacklinks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 链接 */
    @Excel(name = "链接")
    private String links;

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
    public void setLinks(String links) 
    {
        this.links = links;
    }

    public String getLinks() 
    {
        return links;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("links", getLinks())
            .toString();
    }
}
