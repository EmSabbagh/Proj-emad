package com.ruoyi.culture.mapper;

import java.util.List;
import com.ruoyi.culture.domain.CulBacklinks;

/**
 * 外链管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface CulBacklinksMapper 
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
     * 删除外链管理
     * 
     * @param id 外链管理主键
     * @return 结果
     */
    public int deleteCulBacklinksById(Long id);

    /**
     * 批量删除外链管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCulBacklinksByIds(Long[] ids);
}
