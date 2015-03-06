package com.suning.gslb.node.biz.model;
/**
 * 
 * 机器告警历史模型
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class MachineAlarmRecorderModel {
    private int id;
    private int baseId;
    private int deviceId;
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
    
}
