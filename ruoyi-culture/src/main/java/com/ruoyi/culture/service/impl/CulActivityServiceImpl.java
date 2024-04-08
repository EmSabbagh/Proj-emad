package com.ruoyi.culture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culture.mapper.CulActivityMapper;
import com.ruoyi.culture.domain.CulActivity;
import com.ruoyi.culture.service.ICulActivityService;

/**
 * 活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class CulActivityServiceImpl implements ICulActivityService 
{
    @Autowired
    private CulActivityMapper culActivityMapper;

    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    @Override
    public CulActivity selectCulActivityById(Long id)
    {
        return culActivityMapper.selectCulActivityById(id);
    }

    /**
     * 查询活动列表
     * 
     * @param culActivity 活动
     * @return 活动
     */
    @Override
    public List<CulActivity> selectCulActivityList(CulActivity culActivity)
    {
        return culActivityMapper.selectCulActivityList(culActivity);
    }

    /**
     * 新增活动
     * 
     * @param culActivity 活动
     * @return 结果
     */
    @Override
    public int insertCulActivity(CulActivity culActivity)
    {
        return culActivityMapper.insertCulActivity(culActivity);
    }

    /**
     * 修改活动
     * 
     * @param culActivity 活动
     * @return 结果
     */
    @Override
    public int updateCulActivity(CulActivity culActivity)
    {
        return culActivityMapper.updateCulActivity(culActivity);
    }

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键
     * @return 结果
     */
    @Override
    public int deleteCulActivityByIds(Long[] ids)
    {
        return culActivityMapper.deleteCulActivityByIds(ids);
    }

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    @Override
    public int deleteCulActivityById(Long id)
    {
        return culActivityMapper.deleteCulActivityById(id);
    }
}
