package com.suning.gslb.metric.biz.model;

import java.util.Date;
/**
 * 
 * 历史基础告警记录
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseAlarmRecorderModel {
    private int baseId;
    private int paramId;
    private String alarmContent;
    private double currentValue;
    private Date startTime;
    private Date endTime;
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
    public double getCurrentValue() {
        return currentValue;
    }
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
}
