package com.suning.gslb.metric.dao;

import java.util.List;

import com.suning.gslb.metric.biz.model.CDNNodeAlarmModel;
/**
 * 
 * @author Jeremy Lv
 *
 */
public interface CDNNodeAlarmBeanMapper {
    List<CDNNodeAlarmModel> getAllNodeAlarmEntity();
}
