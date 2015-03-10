package com.suning.gslb.metric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.biz.model.MachineAlarmModel;
import com.suning.gslb.metric.dao.MachineAlarmBeanMapper;
import com.suning.gslb.metric.service.MachineAlarmBeanService;

@Service("machineAlarmBeanService")
public class MachineAlarmBeanServiceImpl implements MachineAlarmBeanService {
    @Autowired
    MachineAlarmBeanMapper machineAlarmEntityMapper;
    
    @Override
    public List<MachineAlarmModel> getAllDeviceAlarmEntity() {
        return machineAlarmEntityMapper.getAllDeviceAlarmEntity();
    }

    @Override
    public void insertDeviceAlarmEntity(
            MachineAlarmModel currentDeviceAlarmEntity) {
        machineAlarmEntityMapper.insertDeviceAlarmEntity(currentDeviceAlarmEntity);
    }

    @Override
    public void delete(MachineAlarmModel deviceAlarmEntity) {
        machineAlarmEntityMapper.delete(deviceAlarmEntity);
    }

}
