package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.ExtraData;
import com.cnsuning.gslb.node.model.Metric;


public class MetricInfoHandler {
    /* Metric related info.*/
    private static List<Metric> metric;
    private static Metric currentMetric;
    private static final String METRIC = "METRIC";
    private static final  String METRIC_NAME = "NAME";
    private static final  String METRIC_VAL = "VAL";
    private static final  String METRIC_TYPE = "TYPE";
    private static final  String METRIC_UNITS = "UNITS";
    private static final  String METRIC_TN = "TN";
    private static final  String METRIC_TMAX = "TMAX";
    private static final  String METRIC_DMAX = "DMAX";
    private static final  String METRIC_SLOPE = "SLOPE";
    private static final  String METRIC_SOURCE = "SOURCE";
    
    public List<Metric> getMetric() {
        return metric;
    }
    
    public MetricInfoHandler() {
        metric = new ArrayList<Metric>();
    }

    /* Initial the metric's data */
    public void initMetricInfo(String qName, Attributes attributes) {
        if(METRIC.equals(qName)){
            currentMetric = new Metric();
            currentMetric.setName(attributes.getValue(METRIC_NAME));
            currentMetric.setVal(attributes.getValue(METRIC_VAL));
            currentMetric.setType(attributes.getValue(METRIC_TYPE));
            currentMetric.setUnits(attributes.getValue(METRIC_UNITS));
            currentMetric.setTn(attributes.getValue(METRIC_TN));
            currentMetric.setTmax(attributes.getValue(METRIC_TMAX));
            currentMetric.setDmax(attributes.getValue(METRIC_DMAX));
            currentMetric.setSlope(attributes.getValue(METRIC_SLOPE));
            currentMetric.setSource(attributes.getValue(METRIC_SOURCE));
        }
    }
    
    public void setInfoToMetric(ExtraData extraData, String qName) {
        if(METRIC.equals(qName)){
            currentMetric.setExtra_data(extraData);
            metric.add(currentMetric);
            currentMetric = null;
        }
    }
}
