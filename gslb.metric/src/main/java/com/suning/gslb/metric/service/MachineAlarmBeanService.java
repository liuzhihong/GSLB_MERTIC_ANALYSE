package com.suning.gslb.metric.service;

import java.util.List;

import com.suning.gslb.node.biz.model.MachineAlarmModel;

public interface MachineAlarmBeanService {
    List<MachineAlarmModel> getAllDeviceAlarmEntity();

    void insertDeviceAlarmEntity(MachineAlarmModel currentDeviceAlarmEntity);

    void delete(MachineAlarmModel storedDeviceAlarmEntity);
}
