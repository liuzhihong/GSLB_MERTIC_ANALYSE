package com.cnsuning.gslb.node.biz.model;

import java.util.Date;

public class MetricThresholdModel {
    /**
     * 指标ID
     */
    private int paramId;
    /**
     *指标名称
     */
    private String paramName;
    /**
     *告警阈
     */
    private double alarmValue;
    /**
     * 单位
     */
    private String tag;
    /**
     * 0:lvs 1:ats 2:通用
     */
    private int type;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备注
     */
    private String remark;

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public double getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(double alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
