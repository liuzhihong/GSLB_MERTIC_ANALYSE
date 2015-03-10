package com.suning.gslb.metric.dao;
import java.util.List;

import com.suning.gslb.metric.biz.model.MetricThresholdModel;


/**
 * 
 * @author Jeremy Lv
 *
 */
public interface AlarmThresholdBeanMapper {
    MetricThresholdModel getAlarmValueByParamName(String param_name);
    
    String getParamNameByParamId(int param_Id);
    
    List<MetricThresholdModel> getAllAlarmThreshold();
}
