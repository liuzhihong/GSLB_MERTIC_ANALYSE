package com.suning.gslb.metric.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.dao.BaseAlarmBeanMapper;
import com.suning.gslb.metric.service.BaseAlarmBeanService;
import com.suning.gslb.node.biz.model.BaseAlarmModel;

@Service("baseAlarmBeanService")
public class BaseAlarmBeanServiceImpl implements BaseAlarmBeanService {

    @Autowired
    BaseAlarmBeanMapper baseAlarmEntityMapper;
    
    @Override
    public BaseAlarmModel getBaseAlarmEntity(
            int based_id) {
        return baseAlarmEntityMapper.getBaseAlarmEntity(based_id);
    }

    @Override
    public void insertAlarmEntityAndGetBaseId(BaseAlarmModel baseAlarmEntity) {
        baseAlarmEntityMapper.insertAlarmEntityAndGetBaseId(baseAlarmEntity);
    }

    @Override
    public void updateAlarmTable(BaseAlarmModel baseAlarmEntity) {
        baseAlarmEntityMapper.updateAlarmTable(baseAlarmEntity);
    }

    @Override
    public void delete(BaseAlarmModel baseAlarmEntity) {
        baseAlarmEntityMapper.delete(baseAlarmEntity);
    }

}
