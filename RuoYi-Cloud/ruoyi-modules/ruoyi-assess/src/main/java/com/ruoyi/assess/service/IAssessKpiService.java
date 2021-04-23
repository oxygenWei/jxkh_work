package com.ruoyi.assess.service;

import java.util.List;
import com.ruoyi.assess.domain.AssessKpi;

/**
 * 指标库Service接口
 * 
 * @author ruoyi
 * @date 2021-04-22
 */
public interface IAssessKpiService 
{
    /**
     * 查询指标库
     * 
     * @param kpiId 指标库ID
     * @return 指标库
     */
    public AssessKpi selectAssessKpiById(Long kpiId);

    /**
     * 查询指标库列表
     * 
     * @param assessKpi 指标库
     * @return 指标库集合
     */
    public List<AssessKpi> selectAssessKpiList(AssessKpi assessKpi);

    /**
     * 新增指标库
     * 
     * @param assessKpi 指标库
     * @return 结果
     */
    public int insertAssessKpi(AssessKpi assessKpi);

    /**
     * 修改指标库
     * 
     * @param assessKpi 指标库
     * @return 结果
     */
    public int updateAssessKpi(AssessKpi assessKpi);

    /**
     * 批量删除指标库
     * 
     * @param kpiIds 需要删除的指标库ID
     * @return 结果
     */
    public int deleteAssessKpiByIds(Long[] kpiIds);

    /**
     * 删除指标库信息
     * 
     * @param kpiId 指标库ID
     * @return 结果
     */
    public int deleteAssessKpiById(Long kpiId);
}
