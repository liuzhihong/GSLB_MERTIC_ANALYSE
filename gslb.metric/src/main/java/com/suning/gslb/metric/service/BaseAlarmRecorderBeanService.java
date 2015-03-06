package com.suning.gslb.metric.service;

import com.suning.gslb.node.biz.model.BaseAlarmRecorderModel;


public interface BaseAlarmRecorderBeanService {

    void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity);

}
