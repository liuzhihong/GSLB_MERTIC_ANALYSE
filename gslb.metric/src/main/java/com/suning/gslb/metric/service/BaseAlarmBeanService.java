package com.suning.gslb.metric.service;

import com.cnsuning.gslb.node.biz.model.BaseAlarmModel;


public interface BaseAlarmBeanService {
    BaseAlarmModel getBaseAlarmEntity(int based_id);

    void insertAlarmEntityAndGetBaseId(BaseAlarmModel baseAlarmEntity);

    void updateAlarmTable(BaseAlarmModel baseAlarmEntity);

    void delete(BaseAlarmModel baseAlarmEntity);

}
