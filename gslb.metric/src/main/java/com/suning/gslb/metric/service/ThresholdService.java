package com.suning.gslb.metric.service;

import java.util.Map;

/**
 * 
 * 根据参数值查找指标阈值，调整
 *
 * @author 13073050
 * 
 */
public interface ThresholdService {
    
    public double getThresholdValueByParam(String metricName);
    
    public void modifyThresholdValueByParam(Map<String,Object> param);
}
