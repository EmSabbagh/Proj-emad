package com.ruoyi.culture.service;

import java.util.List;
import com.ruoyi.culture.domain.CulEvent;

/**
 * 特色事件Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface ICulEventService 
{
    /**
     * 查询特色事件
     * 
     * @param id 特色事件主键
     * @return 特色事件
     */
    public CulEvent selectCulEventById(Long id);

    /**
     * 查询特色事件列表
     * 
     * @param culEvent 特色事件
     * @return 特色事件集合
     */
    public List<CulEvent> selectCulEventList(CulEvent culEvent);

    /**
     * 新增特色事件
     * 
     * @param culEvent 特色事件
     * @return 结果
     */
    public int insertCulEvent(CulEvent culEvent);

    /**
     * 修改特色事件
     * 
     * @param culEvent 特色事件
     * @return 结果
     */
    public int updateCulEvent(CulEvent culEvent);

    /**
     * 批量删除特色事件
     * 
     * @param ids 需要删除的特色事件主键集合
     * @return 结果
     */
    public int deleteCulEventByIds(Long[] ids);

    /**
     * 删除特色事件信息
     * 
     * @param id 特色事件主键
     * @return 结果
     */
    public int deleteCulEventById(Long id);
}
