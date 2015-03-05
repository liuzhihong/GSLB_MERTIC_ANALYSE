package com.suning.gslb.metric.service;

import java.util.List;

import com.cnsuning.gslb.node.biz.model.CDNNodeAlarmModel;

public interface CDNNodeAlarmBeanService {
    List<CDNNodeAlarmModel> getAllNodeAlarmEntity();
}
