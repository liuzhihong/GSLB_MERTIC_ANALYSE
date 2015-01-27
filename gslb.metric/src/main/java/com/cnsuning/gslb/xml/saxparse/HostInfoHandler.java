package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.Host;
import com.cnsuning.gslb.node.model.Metric;

public class HostInfoHandler {
    /* Host related info.*/
    private static List<Host> host;
    private static Host currentHost;
    private static final String HOST = "HOST";
    private static final  String HOST_NAME = "NAME";
    private static final  String HOST_IP = "IP";
    private static final  String HOST_REPORTED = "REPORTED";
    private static final  String HOST_TN = "TN";
    private static final  String HOST_TMAX = "TMAX";
    private static final  String HOST_DMAX = "DMAX";
    private static final  String HOST_LOCATION = "LOCATION";
    private static final  String HOST_GMOND_STARTED = "GMOND_STARTED";
    private static final  String HOST_TAGS = "TAGS";
    
    public List<Host> getHost() {
        return host;
    }
    
    public HostInfoHandler() {
        host = new ArrayList<Host>();
    }
    
    /* Initial the host's data */
    public void initHostInfo(String qName, Attributes attributes) {
        if(HOST.equals(qName)){
            currentHost = new Host();
            currentHost.setName(attributes.getValue(HOST_NAME));
            currentHost.setIp(attributes.getValue(HOST_IP));
            currentHost.setReported(attributes.getValue(HOST_REPORTED));
            currentHost.setTn(attributes.getValue(HOST_TN));
            currentHost.setTmax(attributes.getValue(HOST_TMAX));
            currentHost.setDmax(attributes.getValue(HOST_DMAX));
            currentHost.setLocation(attributes.getValue(HOST_LOCATION));
            currentHost.setGmond_stated(attributes.getValue(HOST_GMOND_STARTED));
            currentHost.setTags(attributes.getValue(HOST_TAGS));
        }
    }
    
    public void setInfoToHost(List<Metric> metric, String qName) {
        if(HOST.equals(qName)){
            currentHost.setMetric(metric);
            host.add(currentHost);
            currentHost = null;
        }
    }
}
