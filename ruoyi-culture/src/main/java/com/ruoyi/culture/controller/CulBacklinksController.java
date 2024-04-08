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
import com.ruoyi.culture.domain.CulBacklinks;
import com.ruoyi.culture.service.ICulBacklinksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 外链管理Controller
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("/culture/backlinks")
public class CulBacklinksController extends BaseController
{
    @Autowired
    private ICulBacklinksService culBacklinksService;

    /**
     * 查询外链管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CulBacklinks culBacklinks)
    {
        startPage();
        List<CulBacklinks> list = culBacklinksService.selectCulBacklinksList(culBacklinks);
        return getDataTable(list);
    }

    /**
     * 导出外链管理列表
     */
    @Log(title = "外链管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulBacklinks culBacklinks)
    {
        List<CulBacklinks> list = culBacklinksService.selectCulBacklinksList(culBacklinks);
        ExcelUtil<CulBacklinks> util = new ExcelUtil<CulBacklinks>(CulBacklinks.class);
        util.exportExcel(response, list, "外链管理数据");
    }

    /**
     * 获取外链管理详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(culBacklinksService.selectCulBacklinksById(id));
    }

    /**
     * 新增外链管理
     */
    @Log(title = "外链管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulBacklinks culBacklinks)
    {
        return toAjax(culBacklinksService.insertCulBacklinks(culBacklinks));
    }

    /**
     * 修改外链管理
     */
    @Log(title = "外链管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulBacklinks culBacklinks)
    {
        return toAjax(culBacklinksService.updateCulBacklinks(culBacklinks));
    }

    /**
     * 删除外链管理
     */
    @Log(title = "外链管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(culBacklinksService.deleteCulBacklinksByIds(ids));
    }
}
