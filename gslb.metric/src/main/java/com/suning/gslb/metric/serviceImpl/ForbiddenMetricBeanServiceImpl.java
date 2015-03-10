package com.suning.gslb.metric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.biz.model.ForbiddenMetricModel;
import com.suning.gslb.metric.dao.ForbiddenMetricBeanMapper;
import com.suning.gslb.metric.service.ForbiddenMetricBeanService;

@Service("forbiddenMetricBeanService")
public class ForbiddenMetricBeanServiceImpl implements ForbiddenMetricBeanService {
    
    @Autowired
    private ForbiddenMetricBeanMapper forbiddenMetricBeanMapper;
    
    @Override
    public List<ForbiddenMetricModel> getAllForbiddenMetric() {
        return forbiddenMetricBeanMapper.getAllForbiddenMetrics();
    }

    @Override
    public int addForbiddenMetric(int paramId, int deviceId) {
        // TODO Auto-generated method stub
        return 0;
    }

}
