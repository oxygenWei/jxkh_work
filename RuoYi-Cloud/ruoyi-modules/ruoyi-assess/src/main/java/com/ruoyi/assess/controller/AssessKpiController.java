package com.ruoyi.assess.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.assess.domain.AssessKpi;
import com.ruoyi.assess.service.IAssessKpiService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 指标库Controller
 * 
 * @author ruoyi
 * @date 2021-04-22
 */
@RestController
@RequestMapping("/kpi")
public class AssessKpiController extends BaseController
{
    @Autowired
    private IAssessKpiService assessKpiService;

    /**
     * 查询指标库列表
     */
    @PreAuthorize(hasPermi = "assess:kpi:list")
    @GetMapping("/list")
    public TableDataInfo list(AssessKpi assessKpi)
    {
        startPage();
        List<AssessKpi> list = assessKpiService.selectAssessKpiList(assessKpi);
        return getDataTable(list);
    }

    /**
     * 导出指标库列表
     */
    @PreAuthorize(hasPermi = "assess:kpi:export")
    @Log(title = "指标库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssessKpi assessKpi) throws IOException
    {
        List<AssessKpi> list = assessKpiService.selectAssessKpiList(assessKpi);
        ExcelUtil<AssessKpi> util = new ExcelUtil<AssessKpi>(AssessKpi.class);
        util.exportExcel(response, list, "指标库数据");
    }

    /**
     * 获取指标库详细信息
     */
    @PreAuthorize(hasPermi = "assess:kpi:query")
    @GetMapping(value = "/{kpiId}")
    public AjaxResult getInfo(@PathVariable("kpiId") Long kpiId)
    {
        return AjaxResult.success(assessKpiService.selectAssessKpiById(kpiId));
    }

    /**
     * 新增指标库
     */
    @PreAuthorize(hasPermi = "assess:kpi:add")
    @Log(title = "指标库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssessKpi assessKpi)
    {
        return toAjax(assessKpiService.insertAssessKpi(assessKpi));
    }

    /**
     * 修改指标库
     */
    @PreAuthorize(hasPermi = "assess:kpi:edit")
    @Log(title = "指标库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssessKpi assessKpi)
    {
        return toAjax(assessKpiService.updateAssessKpi(assessKpi));
    }

    /**
     * 删除指标库
     */
    @PreAuthorize(hasPermi = "assess:kpi:remove")
    @Log(title = "指标库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{kpiIds}")
    public AjaxResult remove(@PathVariable Long[] kpiIds)
    {
        return toAjax(assessKpiService.deleteAssessKpiByIds(kpiIds));
    }
}
