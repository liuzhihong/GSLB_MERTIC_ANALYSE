package com.suning.gslb.metric.service;

import java.util.Map;

/**
 * 
 * �澯��ֵ�����:������Ӧָ����ֵ�Ĳ��ҡ�����...
 *
 * @author 13073050
 * @see [�����/����]����ѡ��
 * @since [��Ʒ/ģ��汾] ����ѡ��
 */
public interface ThresholdService {
    
    public double getThresholdValueByParam(String metricName);
    
    public void modifyThresholdValueByParam(Map<String,Object> param);
}
