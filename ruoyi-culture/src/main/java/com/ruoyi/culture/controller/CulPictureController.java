package com.ruoyi.culture.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.culture.domain.CulPicture;
import com.ruoyi.culture.service.ICulPictureService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 画廊列表Controller
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("/culture/picture")
public class CulPictureController extends BaseController
{
    @Autowired
    private ICulPictureService culPictureService;

    /**
     * 查询画廊列表列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CulPicture culPicture)
    {
        startPage();
        List<CulPicture> list = culPictureService.selectCulPictureList(culPicture);
        return getDataTable(list);
    }

    /**
     * 导出画廊列表列表
     */
    @Log(title = "画廊列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulPicture culPicture)
    {
        List<CulPicture> list = culPictureService.selectCulPictureList(culPicture);
        ExcelUtil<CulPicture> util = new ExcelUtil<CulPicture>(CulPicture.class);
        util.exportExcel(response, list, "画廊列表数据");
    }

    /**
     * 获取画廊列表详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(culPictureService.selectCulPictureById(id));
    }

    /**
     * 新增画廊列表
     */
    @Log(title = "画廊列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulPicture culPicture)
    {
        return toAjax(culPictureService.insertCulPicture(culPicture));
    }

    /**
     * 修改画廊列表
     */
    @Log(title = "画廊列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulPicture culPicture)
    {
        return toAjax(culPictureService.updateCulPicture(culPicture));
    }

    /**
     * 删除画廊列表
     */
    @Log(title = "画廊列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(culPictureService.deleteCulPictureByIds(ids));
    }
}
