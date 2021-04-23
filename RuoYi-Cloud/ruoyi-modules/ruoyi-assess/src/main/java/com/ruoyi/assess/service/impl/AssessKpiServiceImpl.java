package com.ruoyi.assess.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assess.mapper.AssessKpiMapper;
import com.ruoyi.assess.domain.AssessKpi;
import com.ruoyi.assess.service.IAssessKpiService;

/**
 * 指标库Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-22
 */
@Service
public class AssessKpiServiceImpl implements IAssessKpiService 
{
    @Autowired
    private AssessKpiMapper assessKpiMapper;

    /**
     * 查询指标库
     * 
     * @param kpiId 指标库ID
     * @return 指标库
     */
    @Override
    public AssessKpi selectAssessKpiById(Long kpiId)
    {
        return assessKpiMapper.selectAssessKpiById(kpiId);
    }

    /**
     * 查询指标库列表
     * 
     * @param assessKpi 指标库
     * @return 指标库
     */
    @Override
    public List<AssessKpi> selectAssessKpiList(AssessKpi assessKpi)
    {
        return assessKpiMapper.selectAssessKpiList(assessKpi);
    }

    /**
     * 新增指标库
     * 
     * @param assessKpi 指标库
     * @return 结果
     */
    @Override
    public int insertAssessKpi(AssessKpi assessKpi)
    {
        return assessKpiMapper.insertAssessKpi(assessKpi);
    }

    /**
     * 修改指标库
     * 
     * @param assessKpi 指标库
     * @return 结果
     */
    @Override
    public int updateAssessKpi(AssessKpi assessKpi)
    {
        return assessKpiMapper.updateAssessKpi(assessKpi);
    }

    /**
     * 批量删除指标库
     * 
     * @param kpiIds 需要删除的指标库ID
     * @return 结果
     */
    @Override
    public int deleteAssessKpiByIds(Long[] kpiIds)
    {
        return assessKpiMapper.deleteAssessKpiByIds(kpiIds);
    }

    /**
     * 删除指标库信息
     * 
     * @param kpiId 指标库ID
     * @return 结果
     */
    @Override
    public int deleteAssessKpiById(Long kpiId)
    {
        return assessKpiMapper.deleteAssessKpiById(kpiId);
    }
}
