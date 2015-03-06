package com.suning.gslb.node.data.model;

import java.util.ArrayList;
import java.util.List;

public class HostEntity {
    private List<MetricEntity> metric = new ArrayList<MetricEntity>();
    private String name;
    private String ip;
    private String reported;
    private String tn;
    private String tmax;
    private String dmax;
    private String location;
    private String gmond_stated;
    private String tags;
    
    public List<MetricEntity> getMetric() {
        return metric;
    }
    
    public void setMetric(List<MetricEntity> metric) {
        this.metric = metric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getReported() {
        return reported;
    }

    public void setReported(String reported) {
        this.reported = reported;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGmond_stated() {
        return gmond_stated;
    }

    public void setGmond_stated(String gmond_stated) {
        this.gmond_stated = gmond_stated;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    
    
}
