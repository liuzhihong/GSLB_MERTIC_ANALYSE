package com.suning.gslb.metric.service;

import java.util.List;

import com.suning.gslb.metric.biz.model.MachineModel;

public interface MachineBeanService {
    MachineModel getHostEntityByName(String device_name);

    String getDeviceNameByDeviceId(int device_Id);

    List<MachineModel> getAllHostEntity();
}
