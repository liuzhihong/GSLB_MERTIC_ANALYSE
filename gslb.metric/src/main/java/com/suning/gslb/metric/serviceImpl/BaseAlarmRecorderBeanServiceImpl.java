package com.suning.gslb.metric.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.dao.BaseAlarmRecorderBeanMapper;
import com.suning.gslb.metric.service.BaseAlarmRecorderBeanService;
import com.suning.gslb.node.biz.model.BaseAlarmRecorderModel;

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
