package com.suning.gslb.metric.dao;

import java.util.List;

import com.cnsuning.gslb.node.biz.model.MachineModel;
/**
 * 
 * @author Jeremy Lv
 *
 */
public interface MachineBeanMapper {
    MachineModel getHostEntityByName(String device_name);
    /**
     * 
     * 功能描述: 根据机器ID拿到对应的机器名称
     *
     * @param device_Id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    String getDeviceNameByDeviceId(int device_Id);
    
    List<MachineModel> getAllHostEntity();
}
