package com.cnsuning.gslb.node.biz.model;

import java.util.Date;

public class WaterLevelRecorderModel {
    /**
     * 水位记录ID
     */
    private int id;
    /**
     * 节点编码
     */
    private int nodeId;
    /**
     * 当前值
     */
    private double currentValue;
    /**
     *上次值
     */
    private double lastValue;
    /**
     *创建时间
     */
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
