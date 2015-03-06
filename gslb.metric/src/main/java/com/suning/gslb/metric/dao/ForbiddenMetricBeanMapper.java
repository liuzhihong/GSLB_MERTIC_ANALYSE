package com.suning.gslb.metric.dao;

import java.util.List;

import com.suning.gslb.node.biz.model.ForbiddenMetricModel;

public interface ForbiddenMetricBeanMapper {
    List<ForbiddenMetricModel> getAllForbiddenMetrics();
}
