package com.suning.gslb.metric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suning.gslb.metric.dao.MachineBeanMapper;
import com.suning.gslb.metric.service.MachineBeanService;
import com.suning.gslb.node.biz.model.MachineModel;

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
