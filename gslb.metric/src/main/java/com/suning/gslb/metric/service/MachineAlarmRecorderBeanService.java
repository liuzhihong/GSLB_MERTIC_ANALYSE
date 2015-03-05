package com.suning.gslb.metric.service;

import com.cnsuning.gslb.node.biz.model.MachineAlarmRecorderModel;


public interface MachineAlarmRecorderBeanService {
    void insertDeviceAlarmEntity(MachineAlarmRecorderModel currentDeviceAlarmEntity);
}
