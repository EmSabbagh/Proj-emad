package com.ruoyi.culture.service;

import java.util.List;
import com.ruoyi.culture.domain.CulPicture;

/**
 * 画廊列表Service接口
 * 
 * @author ruoyi
 * @date 2024-03-25
 */
public interface ICulPictureService 
{
    /**
     * 查询画廊列表
     * 
     * @param id 画廊列表主键
     * @return 画廊列表
     */
    public CulPicture selectCulPictureById(Long id);

    /**
     * 查询画廊列表列表
     * 
     * @param culPicture 画廊列表
     * @return 画廊列表集合
     */
    public List<CulPicture> selectCulPictureList(CulPicture culPicture);

    /**
     * 新增画廊列表
     * 
     * @param culPicture 画廊列表
     * @return 结果
     */
    public int insertCulPicture(CulPicture culPicture);

    /**
     * 修改画廊列表
     * 
     * @param culPicture 画廊列表
     * @return 结果
     */
    public int updateCulPicture(CulPicture culPicture);

    /**
     * 批量删除画廊列表
     * 
     * @param ids 需要删除的画廊列表主键集合
     * @return 结果
     */
    public int deleteCulPictureByIds(Long[] ids);

    /**
     * 删除画廊列表信息
     * 
     * @param id 画廊列表主键
     * @return 结果
     */
    public int deleteCulPictureById(Long id);
}
