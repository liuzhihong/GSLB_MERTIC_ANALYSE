package com.suning.gslb.metric.biz.model;

public class CDNNodeModel {
    /**
     * 节点ID
     */
    private int nodeId;
    /**
     * 节点名称
     */
    private String nodeName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 网络运营商 0:电信 1:联通2:移动3:铁通4:教育网 5:其它
     */
    private int networkOperators;
    /**
     * 网络带宽
     */
    private String netBandWidth;
    /**
     * 单机压测QPS
     */
    private double singleCapacity;
    /**
     * 0:启用 1:停用
     */
    private int status;
    /**
     * 备注
     */
    private String remark;

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getNetworkOperators() {
        return networkOperators;
    }

    public void setNetworkOperators(int networkOperators) {
        this.networkOperators = networkOperators;
    }

    public String getNetBandWidth() {
        return netBandWidth;
    }

    public void setNetBandWidth(String netBandWidth) {
        this.netBandWidth = netBandWidth;
    }

    public double getSingleCapacity() {
        return singleCapacity;
    }

    public void setSingleCapacity(double singleCapacity) {
        this.singleCapacity = singleCapacity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
