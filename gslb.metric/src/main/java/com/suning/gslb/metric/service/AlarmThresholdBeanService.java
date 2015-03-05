package com.suning.gslb.metric.service;

import java.util.List;

import com.suning.gslb.common.model.MetricThresholdModel;


public interface AlarmThresholdBeanService {
    MetricThresholdModel getAlarmValueByParamName(String param_name);

    String getParamNameByParamId(int param_Id);

    List<MetricThresholdModel> getAllAlarmThreshold();
}
