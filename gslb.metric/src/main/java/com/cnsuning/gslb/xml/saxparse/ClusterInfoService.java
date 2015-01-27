package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.ClusterInfo;
import com.cnsuning.gslb.node.model.HostInfo;


public class ClusterInfoService {
    /* Cluster related info.*/
    private static List<ClusterInfo> cluster;
    private static ClusterInfo currentCluster;
    private static final String CLUSTER = "CLUSTER";
    private static final String CLUSTER_NAME = "NAME";
    private static final String CLUSTER_LOCALTIME = "LOCALTIME";
    private static final String CLUSTER_OWNER = "OWNER";
    private static final String CLUSTER_LATLONG = "LATLONG";
    private static final String CLUSTER_URL = "URL";
    
    public List<ClusterInfo> getCluster() {
        return cluster;
    }

    public ClusterInfoService() {
        cluster = new ArrayList<ClusterInfo>();
    }

    /* Initial the cluster's data */
    public void initClusterInfo(String qName, Attributes attributes) {
        if(CLUSTER.equals(qName)){
            currentCluster = new ClusterInfo();
            currentCluster.setName(attributes.getValue(CLUSTER_NAME));
            currentCluster.setLatlong(attributes.getValue(CLUSTER_LATLONG));
            currentCluster.setLocaltime(attributes.getValue(CLUSTER_LOCALTIME));
            currentCluster.setOwner(attributes.getValue(CLUSTER_OWNER));
            currentCluster.setUrl(attributes.getValue(CLUSTER_URL));
        }
    }
    
    public void setInfoToCluster(List<HostInfo> host, String qName) {
        if(CLUSTER.equals(qName)){
            currentCluster.setHost(host);
            cluster.add(currentCluster);
            currentCluster = null;
        }
    }
}
