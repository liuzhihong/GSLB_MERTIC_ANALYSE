package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.ExtraDataInfo;
import com.cnsuning.gslb.node.model.MetricInfo;


public class MetricInfoService {
    /* Metric related info.*/
    private static List<MetricInfo> metric;
    private static MetricInfo currentMetric;
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
    
    public List<MetricInfo> getMetric() {
        return metric;
    }
    
    public MetricInfoService() {
        metric = new ArrayList<MetricInfo>();
    }

    /* Initial the metric's data */
    public void initMetricInfo(String qName, Attributes attributes) {
        if(METRIC.equals(qName)){
            currentMetric = new MetricInfo();
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
    
    public void setInfoToMetric(ExtraDataInfo extraData, String qName) {
        if(METRIC.equals(qName)){
            currentMetric.setExtra_data(extraData);
            metric.add(currentMetric);
            currentMetric = null;
        }
    }
}
