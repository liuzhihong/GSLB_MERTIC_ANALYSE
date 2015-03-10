package com.suning.gslb.metric.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.biz.model.BaseAlarmRecorderModel;
import com.suning.gslb.metric.dao.BaseAlarmRecorderBeanMapper;
import com.suning.gslb.metric.service.BaseAlarmRecorderBeanService;

@Service("baseAlarmRecorderBeanService")
public class BaseAlarmRecorderBeanServiceImpl implements
        BaseAlarmRecorderBeanService {
    
    @Autowired
    BaseAlarmRecorderBeanMapper baseAlarmRecorderEntityMapper;

    @Override
    public void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity) {
        baseAlarmRecorderEntityMapper.insertAlarmEntityAndGetBaseId(baseAlarmRecorderEntity);
    }
    
}
