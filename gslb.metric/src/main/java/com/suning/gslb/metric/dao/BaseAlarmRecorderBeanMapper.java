package com.suning.gslb.metric.dao;

import com.cnsuning.gslb.node.biz.model.BaseAlarmRecorderModel;

/**
 * 
 * @author Jeremy Lv
 *
 */
public interface BaseAlarmRecorderBeanMapper {
    void insertAlarmEntityAndGetBaseId(
            BaseAlarmRecorderModel baseAlarmRecorderEntity);
}
