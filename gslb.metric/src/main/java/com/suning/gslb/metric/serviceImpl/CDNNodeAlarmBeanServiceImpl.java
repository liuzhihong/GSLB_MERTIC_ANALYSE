package com.suning.gslb.metric.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnsuning.gslb.node.biz.model.CDNNodeAlarmModel;
import com.suning.gslb.metric.dao.CDNNodeAlarmBeanMapper;
import com.suning.gslb.metric.service.CDNNodeAlarmBeanService;

@Service("cdnNodeAlarmBeanService")
public class CDNNodeAlarmBeanServiceImpl implements CDNNodeAlarmBeanService {
    
    @Autowired
    CDNNodeAlarmBeanMapper nodeLevelAlarmEntityMapper;
    
    @Override
    public List<CDNNodeAlarmModel> getAllNodeAlarmEntity() {
        // TODO Auto-generated method stub
        return nodeLevelAlarmEntityMapper.getAllNodeAlarmEntity();
    }

}
