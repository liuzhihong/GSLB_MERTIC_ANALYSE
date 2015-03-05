package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.ExtraDataEntity;
import com.cnsuning.gslb.node.model.MetricEntity;


public class MetricInfoHandler {
    /* Metric related info.*/
    private static List<MetricEntity> metric;
    private static MetricEntity currentMetric;
    private static final  String METRIC_NAME = "NAME";
    private static final  String METRIC_VAL = "VAL";
    private static final  String METRIC_TYPE = "TYPE";
    private static final  String METRIC_UNITS = "UNITS";
    private static final  String METRIC_TN = "TN";
    private static final  String METRIC_TMAX = "TMAX";
    private static final  String METRIC_DMAX = "DMAX";
    private static final  String METRIC_SLOPE = "SLOPE";
    private static final  String METRIC_SOURCE = "SOURCE";
    
    public List<MetricEntity> getMetric() {
        return metric;
    }
    
    public MetricInfoHandler() {
        metric = new ArrayList<MetricEntity>();
    }

    /**
     * Initial the metric's informations
     * @param attributes
     */
    public void initMetricInfo(Attributes attributes) {
        currentMetric = new MetricEntity();
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
    
    /**
     * Set the extra data under the metric label to the metric bean.
     * @param extraData
     */
    public void setInfoToMetric(ExtraDataEntity extraData) {
        currentMetric.setExtra_data(extraData);
        metric.add(currentMetric);
        currentMetric = null;
    }
    
    public void initMetricList(){
        metric = new ArrayList<MetricEntity>();
    }
}
