package com.suning.gslb.metric.xml.saxparse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.xml.sax.Attributes;

import com.suning.gslb.metric.data.model.ClusterEntity;
import com.suning.gslb.metric.data.model.HostEntity;


public class ClusterInfoHandler {
    /* Cluster related info.*/
    private static List<ClusterEntity> cluster;
    private static ClusterEntity currentCluster;
    private static BlockingQueue<ClusterEntity> queue=new LinkedBlockingQueue<ClusterEntity>(100);
    private static final String CLUSTER_NAME = "NAME";
    private static final String CLUSTER_LOCALTIME = "LOCALTIME";
    private static final String CLUSTER_OWNER = "OWNER";
    private static final String CLUSTER_LATLONG = "LATLONG";
    private static final String CLUSTER_URL = "URL";
    
    public static List<ClusterEntity> getCluster() {
        return cluster;
    }
    
    public ClusterInfoHandler() {
        cluster = new ArrayList<ClusterEntity>();
    }

    /**
     * Initial the cluster's informations
     * @param attributes
     */
    public void initClusterInfo(Attributes attributes) {
        currentCluster = new ClusterEntity();
        currentCluster.setName(attributes.getValue(CLUSTER_NAME));
        currentCluster.setLatlong(attributes.getValue(CLUSTER_LATLONG));
        currentCluster.setLocaltime(attributes.getValue(CLUSTER_LOCALTIME));
        currentCluster.setOwner(attributes.getValue(CLUSTER_OWNER));
        currentCluster.setUrl(attributes.getValue(CLUSTER_URL));
    }
    
    /**
     * Set host's informations to the cluster bean.
     * @param host
     */
    public void setInfoToCluster(List<HostEntity> host) {
        currentCluster.setHost(host);
        cluster.add(currentCluster);
        currentCluster = null;
    }
}
