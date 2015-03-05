package com.suning.gslb.metric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.common.model.MachineModel;
import com.suning.gslb.metric.dao.MachineBeanMapper;
import com.suning.gslb.metric.service.MachineBeanService;

@Service("machineBeanService")
public class MachineBeanServiceImpl implements MachineBeanService {
    @Autowired
    MachineBeanMapper machineBeanMapper;
    
    @Override
    public MachineModel getHostEntityByName(String device_name) {
        return machineBeanMapper.getHostEntityByName(device_name);
    }

    @Override
    public String getDeviceNameByDeviceId(int device_Id) {
        return machineBeanMapper.getDeviceNameByDeviceId(device_Id);
    }

    @Override
    public List<MachineModel> getAllHostEntity() {
        // TODO Auto-generated method stub
        return machineBeanMapper.getAllHostEntity();
    }
    
}
