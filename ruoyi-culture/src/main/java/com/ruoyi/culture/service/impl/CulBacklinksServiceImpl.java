package com.ruoyi.culture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culture.mapper.CulBacklinksMapper;
import com.ruoyi.culture.domain.CulBacklinks;
import com.ruoyi.culture.service.ICulBacklinksService;

/**
 * 外链管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class CulBacklinksServiceImpl implements ICulBacklinksService 
{
    @Autowired
    private CulBacklinksMapper culBacklinksMapper;

    /**
     * 查询外链管理
     * 
     * @param id 外链管理主键
     * @return 外链管理
     */
    @Override
    public CulBacklinks selectCulBacklinksById(Long id)
    {
        return culBacklinksMapper.selectCulBacklinksById(id);
    }

    /**
     * 查询外链管理列表
     * 
     * @param culBacklinks 外链管理
     * @return 外链管理
     */
    @Override
    public List<CulBacklinks> selectCulBacklinksList(CulBacklinks culBacklinks)
    {
        return culBacklinksMapper.selectCulBacklinksList(culBacklinks);
    }

    /**
     * 新增外链管理
     * 
     * @param culBacklinks 外链管理
     * @return 结果
     */
    @Override
    public int insertCulBacklinks(CulBacklinks culBacklinks)
    {
        return culBacklinksMapper.insertCulBacklinks(culBacklinks);
    }

    /**
     * 修改外链管理
     * 
     * @param culBacklinks 外链管理
     * @return 结果
     */
    @Override
    public int updateCulBacklinks(CulBacklinks culBacklinks)
    {
        return culBacklinksMapper.updateCulBacklinks(culBacklinks);
    }

    /**
     * 批量删除外链管理
     * 
     * @param ids 需要删除的外链管理主键
     * @return 结果
     */
    @Override
    public int deleteCulBacklinksByIds(Long[] ids)
    {
        return culBacklinksMapper.deleteCulBacklinksByIds(ids);
    }

    /**
     * 删除外链管理信息
     * 
     * @param id 外链管理主键
     * @return 结果
     */
    @Override
    public int deleteCulBacklinksById(Long id)
    {
        return culBacklinksMapper.deleteCulBacklinksById(id);
    }
}
