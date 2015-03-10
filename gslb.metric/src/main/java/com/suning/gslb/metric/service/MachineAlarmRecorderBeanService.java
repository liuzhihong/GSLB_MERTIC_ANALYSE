package com.suning.gslb.metric.service;

import com.suning.gslb.metric.biz.model.MachineAlarmRecorderModel;


public interface MachineAlarmRecorderBeanService {
    void insertDeviceAlarmEntity(MachineAlarmRecorderModel currentDeviceAlarmEntity);
}
