package com.suning.gslb.common.model;

public class MachineModel {
    /**
     * 机器ID
     */
    private int deviceId;
    /**
     * 机器主机名
     */
    private String deviceName;
    /**
     * 机器IP
     */
    private String deviceIp;
    /**
     * 所属节点ID
     */
    private int nodeId;
    /**
     * 用途0:KVM 1:LVS  2:ATS
     */
    private int useType;
    /**
     * 宿主机：如是物理机为0
     */
    private int parentId;
    
    /**
     * 系统配置
     */
    private String hardwareParam;
    /**
     * 备注
     */
    private String remark;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getUseType() {
        return useType;
    }

    public void setUseType(int useType) {
        this.useType = useType;
    }

    public String getHardwareParam() {
        return hardwareParam;
    }

    public void setHardwareParam(String hardwareParam) {
        this.hardwareParam = hardwareParam;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

}
