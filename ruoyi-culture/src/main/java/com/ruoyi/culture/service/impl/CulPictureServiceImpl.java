package com.ruoyi.culture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.culture.mapper.CulPictureMapper;
import com.ruoyi.culture.domain.CulPicture;
import com.ruoyi.culture.service.ICulPictureService;

/**
 * 画廊列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
@Service
public class CulPictureServiceImpl implements ICulPictureService 
{
    @Autowired
    private CulPictureMapper culPictureMapper;

    /**
     * 查询画廊列表
     * 
     * @param id 画廊列表主键
     * @return 画廊列表
     */
    @Override
    public CulPicture selectCulPictureById(Long id)
    {
        return culPictureMapper.selectCulPictureById(id);
    }

    /**
     * 查询画廊列表列表
     * 
     * @param culPicture 画廊列表
     * @return 画廊列表
     */
    @Override
    public List<CulPicture> selectCulPictureList(CulPicture culPicture)
    {
        return culPictureMapper.selectCulPictureList(culPicture);
    }

    /**
     * 新增画廊列表
     * 
     * @param culPicture 画廊列表
     * @return 结果
     */
    @Override
    public int insertCulPicture(CulPicture culPicture)
    {
        return culPictureMapper.insertCulPicture(culPicture);
    }

    /**
     * 修改画廊列表
     * 
     * @param culPicture 画廊列表
     * @return 结果
     */
    @Override
    public int updateCulPicture(CulPicture culPicture)
    {
        return culPictureMapper.updateCulPicture(culPicture);
    }

    /**
     * 批量删除画廊列表
     * 
     * @param ids 需要删除的画廊列表主键
     * @return 结果
     */
    @Override
    public int deleteCulPictureByIds(Long[] ids)
    {
        return culPictureMapper.deleteCulPictureByIds(ids);
    }

    /**
     * 删除画廊列表信息
     * 
     * @param id 画廊列表主键
     * @return 结果
     */
    @Override
    public int deleteCulPictureById(Long id)
    {
        return culPictureMapper.deleteCulPictureById(id);
    }
}
