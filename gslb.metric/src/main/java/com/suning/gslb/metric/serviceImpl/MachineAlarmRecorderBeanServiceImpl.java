package com.suning.gslb.metric.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnsuning.gslb.node.biz.model.MachineAlarmRecorderModel;
import com.suning.gslb.metric.dao.MachineAlarmRecorderBeanMapper;
import com.suning.gslb.metric.service.MachineAlarmRecorderBeanService;

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
