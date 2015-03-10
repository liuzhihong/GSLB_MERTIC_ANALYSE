package com.suning.gslb.metric.service;

import java.util.List;

import com.suning.gslb.metric.biz.model.CDNNodeAlarmModel;

public interface CDNNodeAlarmBeanService {
    List<CDNNodeAlarmModel> getAllNodeAlarmEntity();
}
