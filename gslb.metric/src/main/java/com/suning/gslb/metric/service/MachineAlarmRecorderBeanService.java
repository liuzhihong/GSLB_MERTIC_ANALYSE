package com.suning.gslb.metric.service;

import com.suning.gslb.node.biz.model.MachineAlarmRecorderModel;


public interface MachineAlarmRecorderBeanService {
    void insertDeviceAlarmEntity(MachineAlarmRecorderModel currentDeviceAlarmEntity);
}
