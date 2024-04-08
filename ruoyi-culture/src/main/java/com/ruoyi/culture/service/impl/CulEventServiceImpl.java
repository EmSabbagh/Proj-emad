package com.ruoyi.culture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culture.mapper.CulEventMapper;
import com.ruoyi.culture.domain.CulEvent;
import com.ruoyi.culture.service.ICulEventService;

/**
 * 特色事件Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class CulEventServiceImpl implements ICulEventService 
{
    @Autowired
    private CulEventMapper culEventMapper;

    /**
     * 查询特色事件
     * 
     * @param id 特色事件主键
     * @return 特色事件
     */
    @Override
    public CulEvent selectCulEventById(Long id)
    {
        return culEventMapper.selectCulEventById(id);
    }

    /**
     * 查询特色事件列表
     * 
     * @param culEvent 特色事件
     * @return 特色事件
     */
    @Override
    public List<CulEvent> selectCulEventList(CulEvent culEvent)
    {
        return culEventMapper.selectCulEventList(culEvent);
    }

    /**
     * 新增特色事件
     * 
     * @param culEvent 特色事件
     * @return 结果
     */
    @Override
    public int insertCulEvent(CulEvent culEvent)
    {
        return culEventMapper.insertCulEvent(culEvent);
    }

    /**
     * 修改特色事件
     * 
     * @param culEvent 特色事件
     * @return 结果
     */
    @Override
    public int updateCulEvent(CulEvent culEvent)
    {
        return culEventMapper.updateCulEvent(culEvent);
    }

    /**
     * 批量删除特色事件
     * 
     * @param ids 需要删除的特色事件主键
     * @return 结果
     */
    @Override
    public int deleteCulEventByIds(Long[] ids)
    {
        return culEventMapper.deleteCulEventByIds(ids);
    }

    /**
     * 删除特色事件信息
     * 
     * @param id 特色事件主键
     * @return 结果
     */
    @Override
    public int deleteCulEventById(Long id)
    {
        return culEventMapper.deleteCulEventById(id);
    }
}
