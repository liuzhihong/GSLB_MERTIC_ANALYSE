package com.suning.gslb.node.biz.model;
/**
 * 
 * 指标禁用模型
 *
 * @author 13073050
 * @see [相关方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ForbiddenMetricModel {
    private int id;
    private int paramId;
    private int deviceId;
    private String remark;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public int getParamId() {
        return paramId;
    }
    public void setParamId(int paramId) {
        this.paramId = paramId;
    }
    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
}
