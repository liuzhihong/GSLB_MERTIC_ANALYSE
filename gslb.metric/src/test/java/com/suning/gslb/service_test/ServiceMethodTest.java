package com.suning.gslb.service_test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.suning.gslb.metric.service.ThresholdService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/spring-mybatis.xml"})
public class ServiceMethodTest {
    @Autowired
    private ThresholdService thresholdService;
    
    @Test
    public void testSelectValue(){
        double v = thresholdService.getThresholdValueByParam("cpu_user");
        System.out.println("·µ»ØÖµÎª: "+v);
    }
    @Test
    public void testUpdateValue(){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("thresholdValue",50.0);
        param.put("metric", "cpu_user");
        thresholdService.modifyThresholdValueByParam(param);
        testSelectValue();
    }
}
