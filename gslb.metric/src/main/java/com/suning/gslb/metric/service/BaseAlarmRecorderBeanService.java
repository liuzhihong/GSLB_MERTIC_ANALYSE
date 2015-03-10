package com.suning.gslb.metric.service;

import com.suning.gslb.metric.biz.model.BaseAlarmRecorderModel;


public interface BaseAlarmRecorderBeanService {

    void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity);

}
