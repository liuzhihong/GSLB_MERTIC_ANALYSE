package com.suning.gslb.metric.dao;

import java.util.List;

import com.suning.gslb.metric.biz.model.ForbiddenMetricModel;

public interface ForbiddenMetricBeanMapper {
    List<ForbiddenMetricModel> getAllForbiddenMetrics();
}
