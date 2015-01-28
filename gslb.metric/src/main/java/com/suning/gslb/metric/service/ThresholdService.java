package com.suning.gslb.metric.service;

import java.util.Map;

/**
 * 
 * 告警阈值服务层:包括对应指标阈值的查找、调整...
 *
 * @author 13073050
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface ThresholdService {
    
    public double getThresholdValueByParam(String metricName);
    
    public void modifyThresholdValueByParam(Map<String,Object> param);
}
