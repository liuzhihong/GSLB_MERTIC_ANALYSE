package com.suning.gslb.metric.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.dao.ThresholdDao;
import com.suning.gslb.metric.service.ThresholdService;

@Service
public class ThresholdServiceImpl implements ThresholdService {
    
    @Autowired
    private ThresholdDao thresholdDao;
    
    @Override
    public double getThresholdValueByParam(String metricName) {
        return thresholdDao.selectThresholdValue(metricName);
    }

    @Override
    public void modifyThresholdValueByParam(Map<String,Object> param) {
        thresholdDao.updateThresholdValue(param);
    }

}
