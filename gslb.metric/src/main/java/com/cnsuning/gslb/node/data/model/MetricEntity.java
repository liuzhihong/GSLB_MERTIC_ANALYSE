package com.cnsuning.gslb.node.data.model;

public class MetricEntity {
    private ExtraDataEntity extra_data = new ExtraDataEntity();
    private String name;
    private String val;
    private String type;
    private String units;
    private String tn;
    private String tmax;
    private String dmax;
    private String slope;
    private String source;

    public ExtraDataEntity getExtra_data() {
        return extra_data;
    }
    public void setExtra_data(ExtraDataEntity extra_data) {
        this.extra_data = extra_data;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVal() {
        return val;
    }
    public void setVal(String val) {
        this.val = val;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUnits() {
        return units;
    }
    public void setUnits(String units) {
        this.units = units;
    }
    public String getTn() {
        return tn;
    }
    public void setTn(String tn) {
        this.tn = tn;
    }
    public String getTmax() {
        return tmax;
    }
    public void setTmax(String tmax) {
        this.tmax = tmax;
    }
    public String getDmax() {
        return dmax;
    }
    public void setDmax(String dmax) {
        this.dmax = dmax;
    }
    public String getSlope() {
        return slope;
    }
    public void setSlope(String slope) {
        this.slope = slope;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    

}
