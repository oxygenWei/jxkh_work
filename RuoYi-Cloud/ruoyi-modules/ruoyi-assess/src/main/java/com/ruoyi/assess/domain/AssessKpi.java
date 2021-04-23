package com.ruoyi.assess.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 指标库对象 assess_kpi
 * 
 * @author ruoyi
 * @date 2021-04-22
 */
public class AssessKpi extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 指标ID */
    private Long kpiId;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String kpiName;

    /** 指标描述 */
    @Excel(name = "指标描述")
    private String kpiDescription;

    /** 指标分数 */
    @Excel(name = "指标分数")
    private String kpiGrading;

    /** 指标权重 */
    @Excel(name = "指标权重")
    private BigDecimal kpiWeight;

    /** 父级指标 */
    @Excel(name = "父级指标")
    private Long kpiParentId;

    /** 0:定量 1：定性 */
    @Excel(name = "0:定量 1：定性")
    private Integer kpiType;

    public void setKpiId(Long kpiId) 
    {
        this.kpiId = kpiId;
    }

    public Long getKpiId() 
    {
        return kpiId;
    }
    public void setKpiName(String kpiName) 
    {
        this.kpiName = kpiName;
    }

    public String getKpiName() 
    {
        return kpiName;
    }
    public void setKpiDescription(String kpiDescription) 
    {
        this.kpiDescription = kpiDescription;
    }

    public String getKpiDescription() 
    {
        return kpiDescription;
    }
    public void setKpiGrading(String kpiGrading) 
    {
        this.kpiGrading = kpiGrading;
    }

    public String getKpiGrading() 
    {
        return kpiGrading;
    }
    public void setKpiWeight(BigDecimal kpiWeight) 
    {
        this.kpiWeight = kpiWeight;
    }

    public BigDecimal getKpiWeight() 
    {
        return kpiWeight;
    }
    public void setKpiParentId(Long kpiParentId) 
    {
        this.kpiParentId = kpiParentId;
    }

    public Long getKpiParentId() 
    {
        return kpiParentId;
    }
    public void setKpiType(Integer kpiType) 
    {
        this.kpiType = kpiType;
    }

    public Integer getKpiType() 
    {
        return kpiType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("kpiId", getKpiId())
            .append("kpiName", getKpiName())
            .append("kpiDescription", getKpiDescription())
            .append("kpiGrading", getKpiGrading())
            .append("kpiWeight", getKpiWeight())
            .append("kpiParentId", getKpiParentId())
            .append("kpiType", getKpiType())
            .toString();
    }
}
