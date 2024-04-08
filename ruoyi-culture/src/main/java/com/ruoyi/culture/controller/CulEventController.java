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
import com.ruoyi.culture.domain.CulEvent;
import com.ruoyi.culture.service.ICulEventService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 特色事件Controller
 *
 * @author ruoyi
 * @date 2024-03-25
 */
@RestController
@RequestMapping("/culture/event")
public class CulEventController extends BaseController
{
    @Autowired
    private ICulEventService culEventService;

    /**
     * 查询特色事件列表
     */
    @GetMapping("/list")
    public TableDataInfo list(CulEvent culEvent)
    {
        startPage();
        List<CulEvent> list = culEventService.selectCulEventList(culEvent);
        return getDataTable(list);
    }

    /**
     * 导出特色事件列表
     */
    @Log(title = "特色事件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CulEvent culEvent)
    {
        List<CulEvent> list = culEventService.selectCulEventList(culEvent);
        ExcelUtil<CulEvent> util = new ExcelUtil<CulEvent>(CulEvent.class);
        util.exportExcel(response, list, "特色事件数据");
    }

    /**
     * 获取特色事件详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(culEventService.selectCulEventById(id));
    }

    /**
     * 新增特色事件
     */
    @Log(title = "特色事件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CulEvent culEvent)
    {
        culEvent.setPublishUser(this.getUserId());
        return toAjax(culEventService.insertCulEvent(culEvent));
    }

    /**
     * 修改特色事件
     */
    @Log(title = "特色事件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CulEvent culEvent)
    {
        return toAjax(culEventService.updateCulEvent(culEvent));
    }

    /**
     * 删除特色事件
     */
    @Log(title = "特色事件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(culEventService.deleteCulEventByIds(ids));
    }
}
