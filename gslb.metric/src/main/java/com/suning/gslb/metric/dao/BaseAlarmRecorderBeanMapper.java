package com.suning.gslb.metric.dao;

import com.suning.gslb.metric.biz.model.BaseAlarmRecorderModel;

/**
 * 
 * @author Jeremy Lv
 *
 */
public interface BaseAlarmRecorderBeanMapper {
    void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity);
}
