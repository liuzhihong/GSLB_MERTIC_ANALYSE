package com.suning.gslb.metric.dao;

import java.util.List;

import com.cnsuning.gslb.node.biz.model.MachineAlarmModel;
/**
 * 
 * @author Jeremy Lv
 *
 */
public interface MachineAlarmBeanMapper {
    List<MachineAlarmModel> getAllDeviceAlarmEntity();

    void insertDeviceAlarmEntity(MachineAlarmModel currentDeviceAlarmEntity);
    
    void delete(MachineAlarmModel deviceAlarmEntity);
}
