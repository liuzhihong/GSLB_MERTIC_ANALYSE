package com.suning.gslb.metric.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.biz.model.MetricThresholdModel;
import com.suning.gslb.metric.dao.AlarmThresholdBeanMapper;
import com.suning.gslb.metric.service.AlarmThresholdBeanService;

@Service("alarmThresholdBeanService")
public class AlarmThresholdBeanServiceImpl implements AlarmThresholdBeanService{
    @Autowired
    private AlarmThresholdBeanMapper alarmThresholdEntityMapper;

    public MetricThresholdModel getAlarmValueByParamName(String param_name) {
        return alarmThresholdEntityMapper.getAlarmValueByParamName(param_name);
    }

    @Override
    public String getParamNameByParamId(int param_Id) {
        return alarmThresholdEntityMapper.getParamNameByParamId(param_Id);
    }

    @Override
    public List<MetricThresholdModel> getAllAlarmThreshold() {
        return alarmThresholdEntityMapper.getAllAlarmThreshold();
    }
}
