package com.suning.gslb.metric.service;

import java.util.List;

import com.suning.gslb.node.biz.model.ForbiddenMetricModel;

public interface ForbiddenMetricBeanService {
    /**
     * 
     * 获取所有被禁用的指标类型
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    List<ForbiddenMetricModel> getAllForbiddenMetric();
    /**
     * 
     *   增加一条禁用的指标
     *
     * @param paramId
     * @param deviceId
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    int addForbiddenMetric(int paramId,int deviceId);
}
