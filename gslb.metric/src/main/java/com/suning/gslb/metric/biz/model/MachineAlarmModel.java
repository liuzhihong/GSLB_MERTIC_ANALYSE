package com.suning.gslb.metric.biz.model;

public class MachineAlarmModel {
    private int id;
    /**
     * 告警参数ID
     */
    private int baseId;
    /**
     * 机器信息ID
     */
    private int deviceId;
    private BaseAlarmModel baseAlarmEntityList;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getBaseId() {
        return baseId;
    }
    public void setBaseId(int baseId) {
        this.baseId = baseId;
    }
    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public BaseAlarmModel getBaseAlarmEntity() {
        return baseAlarmEntityList;
    }
    public void setBaseAlarmEntity(BaseAlarmModel baseAlarmEntityList) {
        this.baseAlarmEntityList = baseAlarmEntityList;
    }
}
