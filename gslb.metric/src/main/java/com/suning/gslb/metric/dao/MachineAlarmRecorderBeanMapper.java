package com.suning.gslb.metric.dao;

import com.suning.gslb.node.biz.model.MachineAlarmRecorderModel;

/**
 * 
 * @author Jeremy Lv
 *
 */
public interface MachineAlarmRecorderBeanMapper {
    void insertDeviceAlarmEntity(MachineAlarmRecorderModel currentDeviceAlarmEntity);
}
