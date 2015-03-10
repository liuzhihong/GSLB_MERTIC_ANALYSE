package com.suning.gslb.metric.data.model;

import java.util.ArrayList;
import java.util.List;

public class ClusterEntity {
    private List<HostEntity> host = new ArrayList<HostEntity>();
    private String name;
    private String localtime;
    private String owner;
    private String latlong;
    private String url;

    public List<HostEntity> getHost() {
        return host;
    }
    public void setHost(List<HostEntity> host) {
        this.host = host;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocaltime() {
        return localtime;
    }
    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getLatlong() {
        return latlong;
    }
    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
}
