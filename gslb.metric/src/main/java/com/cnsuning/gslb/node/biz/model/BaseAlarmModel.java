package com.cnsuning.gslb.node.biz.model;

import java.util.Date;

public class BaseAlarmModel {
    /**
     * 基础告警ID
     */
    private int baseId;
    /**
     * 告警指标ID
     */
    private int paramId;
    /**
     * 告警内容
     */
    private String alarmContent;
    /**
     * 告警开始时间
     */
    private Date startTime;
    /**
     *当前告警值
     */
    private double currentValue;

    public int getBaseId() {
        return baseId;
    }

    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getAlarmContent() {
        return alarmContent;
    }

    public void setAlarmContent(String alarmContent) {
        this.alarmContent = alarmContent;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

}
