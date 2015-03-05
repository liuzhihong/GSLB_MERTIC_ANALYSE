package com.suning.gslb.metric.service;

import com.cnsuning.gslb.node.biz.model.BaseAlarmRecorderModel;


public interface BaseAlarmRecorderBeanService {

    void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity);

}
