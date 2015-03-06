package com.suning.gslb.metric.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.dao.MachineAlarmRecorderBeanMapper;
import com.suning.gslb.metric.service.MachineAlarmRecorderBeanService;
import com.suning.gslb.node.biz.model.MachineAlarmRecorderModel;

@Service("machineAlarmRecorderBeanService")
public class MachineAlarmRecorderBeanServiceImpl implements MachineAlarmRecorderBeanService {
    @Autowired
    MachineAlarmRecorderBeanMapper machineAlarmRecorderBeanMapper;

    @Override
    public void insertDeviceAlarmEntity(
            MachineAlarmRecorderModel currentDeviceAlarmRecorderEntity) {
        machineAlarmRecorderBeanMapper.insertDeviceAlarmEntity(currentDeviceAlarmRecorderEntity);
    }

}
