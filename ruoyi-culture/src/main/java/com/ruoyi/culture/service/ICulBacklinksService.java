package com.ruoyi.culture.service;

import java.util.List;
import com.ruoyi.culture.domain.CulBacklinks;

/**
 * 外链管理Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface ICulBacklinksService 
{
    /**
     * 查询外链管理
     * 
     * @param id 外链管理主键
     * @return 外链管理
     */
    public CulBacklinks selectCulBacklinksById(Long id);

    /**
     * 查询外链管理列表
     * 
     * @param culBacklinks 外链管理
     * @return 外链管理集合
     */
    public List<CulBacklinks> selectCulBacklinksList(CulBacklinks culBacklinks);

    /**
     * 新增外链管理
     * 
     * @param culBacklinks 外链管理
     * @return 结果
     */
    public int insertCulBacklinks(CulBacklinks culBacklinks);

    /**
     * 修改外链管理
     * 
     * @param culBacklinks 外链管理
     * @return 结果
     */
    public int updateCulBacklinks(CulBacklinks culBacklinks);

    /**
     * 批量删除外链管理
     * 
     * @param ids 需要删除的外链管理主键集合
     * @return 结果
     */
    public int deleteCulBacklinksByIds(Long[] ids);

    /**
     * 删除外链管理信息
     * 
     * @param id 外链管理主键
     * @return 结果
     */
    public int deleteCulBacklinksById(Long id);
}
