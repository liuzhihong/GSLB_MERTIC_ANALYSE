package com.suning.gslb.metric.biz.model;

public class CDNNodeAlarmModel {
    private int id;
    private int baseId;
    private int nodeId;
    private BaseAlarmModel baseAlarmEntity;
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
    public int getNodeId() {
        return nodeId;
    }
    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }
    public BaseAlarmModel getBaseAlarmEntityList() {
        return baseAlarmEntity;
    }
    public void setBaseAlarmEntity(BaseAlarmModel baseAlarmEntityList) {
        this.baseAlarmEntity = baseAlarmEntityList;
    }
}
