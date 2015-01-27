package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.Cluster;
import com.cnsuning.gslb.node.model.Host;


public class ClusterInfoHandler {
    /* Cluster related info.*/
    private static List<Cluster> cluster;
    private static Cluster currentCluster;
    private static final String CLUSTER = "CLUSTER";
    private static final String CLUSTER_NAME = "NAME";
    private static final String CLUSTER_LOCALTIME = "LOCALTIME";
    private static final String CLUSTER_OWNER = "OWNER";
    private static final String CLUSTER_LATLONG = "LATLONG";
    private static final String CLUSTER_URL = "URL";
    
    public List<Cluster> getCluster() {
        return cluster;
    }

    public ClusterInfoHandler() {
        cluster = new ArrayList<Cluster>();
    }

    /* Initial the cluster's data */
    public void initClusterInfo(String qName, Attributes attributes) {
        if(CLUSTER.equals(qName)){
            currentCluster = new Cluster();
            currentCluster.setName(attributes.getValue(CLUSTER_NAME));
            currentCluster.setLatlong(attributes.getValue(CLUSTER_LATLONG));
            currentCluster.setLocaltime(attributes.getValue(CLUSTER_LOCALTIME));
            currentCluster.setOwner(attributes.getValue(CLUSTER_OWNER));
            currentCluster.setUrl(attributes.getValue(CLUSTER_URL));
        }
    }
    
    public void setInfoToCluster(List<Host> host, String qName) {
        if(CLUSTER.equals(qName)){
            currentCluster.setHost(host);
            cluster.add(currentCluster);
            currentCluster = null;
        }
    }
}
