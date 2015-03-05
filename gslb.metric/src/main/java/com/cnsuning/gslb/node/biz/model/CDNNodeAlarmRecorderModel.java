package com.cnsuning.gslb.node.biz.model;
/**
 * 
 * 节点水位历史告警表
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CDNNodeAlarmRecorderModel {
    private int id;
    private int baseId;
    private int nodeId;
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
    
}
