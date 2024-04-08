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
import com.ruoyi.culture.domain.CulActivity;
import com.ruoyi.culture.service.ICulActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动Controller
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("/culture/activity")
public class CulActivityController extends BaseController
{
    @Autowired
    private ICulActivityService culActivityService;

    /**
     * 查询活动列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CulActivity culActivity)
    {
        startPage();
        List<CulActivity> list = culActivityService.selectCulActivityList(culActivity);
        return getDataTable(list);
    }

    /**
     * 导出活动列表
     */
    @Log(title = "活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulActivity culActivity)
    {
        List<CulActivity> list = culActivityService.selectCulActivityList(culActivity);
        ExcelUtil<CulActivity> util = new ExcelUtil<CulActivity>(CulActivity.class);
        util.exportExcel(response, list, "活动数据");
    }

    /**
     * 获取活动详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(culActivityService.selectCulActivityById(id));
    }

    /**
     * 新增活动
     */
    @Log(title = "活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulActivity culActivity)
    {
        culActivity.setPublishUser(this.getUserId());
        return toAjax(culActivityService.insertCulActivity(culActivity));
    }

    /**
     * 修改活动
     */
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulActivity culActivity)
    {
        return toAjax(culActivityService.updateCulActivity(culActivity));
    }

    /**
     * 删除活动
     */
    @Log(title = "活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(culActivityService.deleteCulActivityByIds(ids));
    }
}
