package com.cnsuning.gslb.node.model;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Host> host = new ArrayList<Host>();
    private String name;
    private String localtime;
    private String owner;
    private String latlong;
    private String url;

    public List<Host> getHost() {
        return host;
    }
    public void setHost(List<Host> host) {
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
