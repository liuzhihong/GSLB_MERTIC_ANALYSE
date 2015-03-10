package com.suning.gslb.metric.dao;

import com.suning.gslb.metric.biz.model.BaseAlarmModel;


/**
 * 
 * @author Jeremy Lv
 *
 */
public interface BaseAlarmBeanMapper {
    BaseAlarmModel getBaseAlarmEntity(int based_id);
    
    void insertAlarmEntityAndGetBaseId(BaseAlarmModel baseAlarmEntity);
    
    void updateAlarmTable(BaseAlarmModel baseAlarmEntity);
    
    void delete(BaseAlarmModel baseAlarmEntity);
}
