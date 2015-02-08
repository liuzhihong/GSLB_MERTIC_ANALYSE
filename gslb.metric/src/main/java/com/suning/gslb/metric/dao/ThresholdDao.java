package com.suning.gslb.metric.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ThresholdDao{
    
    double selectThresholdValue(@Param(value="metricName") String metricName);
    
    void updateThresholdValue(Map<String,Object> param);
}
