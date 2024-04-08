package com.ruoyi.culture.service;

import java.util.List;
import com.ruoyi.culture.domain.CulActivity;

/**
 * 活动Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface ICulActivityService 
{
    /**
     * 查询活动
     * 
     * @param id 活动主键
     * @return 活动
     */
    public CulActivity selectCulActivityById(Long id);

    /**
     * 查询活动列表
     * 
     * @param culActivity 活动
     * @return 活动集合
     */
    public List<CulActivity> selectCulActivityList(CulActivity culActivity);

    /**
     * 新增活动
     * 
     * @param culActivity 活动
     * @return 结果
     */
    public int insertCulActivity(CulActivity culActivity);

    /**
     * 修改活动
     * 
     * @param culActivity 活动
     * @return 结果
     */
    public int updateCulActivity(CulActivity culActivity);

    /**
     * 批量删除活动
     * 
     * @param ids 需要删除的活动主键集合
     * @return 结果
     */
    public int deleteCulActivityByIds(Long[] ids);

    /**
     * 删除活动信息
     * 
     * @param id 活动主键
     * @return 结果
     */
    public int deleteCulActivityById(Long id);
}
