package com.suning.gslb.node.collection.main;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.suning.gslb.metric.serviceImpl.ServiceDispatcher;
import com.suning.gslb.node.data.model.ClusterEntity;
import com.suning.gslb.xml.saxparse.ClusterInfoHandler;
import com.suning.gslb.xml.saxparse.GmatedDataParse;

/**
 * 
 * @author Jeremy Lv
 *
 */
public class TaskManager {
    private static List<ClusterEntity> currentClusterEntityList;
    private static HashMap<String, Integer> hostMap = new HashMap<String, Integer>();
    private static TaskManager inst = new TaskManager();
    private static ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:spring/spring-mybatis.xml"});
    private static final String GMATEDHOST = "gmated.yangzhou";
    private static final String COLLECTEDPERIOD = "collect.period";
    private static final String FILEPATH = "/conf/common_conf.properties";
    private static final String SPITSYMBOL = ":";
    private static int periodTime = 0;
    
    private static final Logger logger = Logger.getLogger(TaskManager.class);
    
    public static TaskManager getInstance() {
        currentClusterEntityList = new ArrayList<ClusterEntity>();
        return inst;
    }

    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.readProperties();
        taskManager.init();
        
    }
    
    private void init() {
        for(Map.Entry<String, Integer> entry : hostMap.entrySet()){
            String ip = entry.getKey();
            int port = entry.getValue();
            initGmatedData(ip, port);
            initDB(); 
        }
    }

    private void initGmatedData(String ip, int port) {
        GmatedDataParse gmatedDataParse = GmatedDataParse.getInstance();
        
        gmatedDataParse.startParse(ip, port);
        List<ClusterEntity> clusterEntityList = ClusterInfoHandler.getCluster();
        for(ClusterEntity cluster : clusterEntityList){
            currentClusterEntityList.add(cluster);
        }
    }
    
    private void initDB() {
        ServiceDispatcher serviceDispacher = ServiceDispatcher.getInstance(context);
        //程序初始化的时候先从数据库把相关的阈值模型和告警模型加载到内存当中
        serviceDispacher.initServiceDispatcher(currentClusterEntityList);

      
        //首次数据解析，处理，存入数据库当中
        serviceDispacher.initAlarmTable();

        //后续指标分析：1、超过阈值，更新指标值；2、低于阈值，警告解除，移入历史表
        serviceDispacher.updateAlarmTable();
        //若有新的监控指标告警，插入新的告警记录
        serviceDispacher.insertItemsToAlarmTable();
    }

    private void readProperties() {
        Properties props = new Properties();
        try {
            InputStream in = this.getClass().getResourceAsStream(FILEPATH); 
            props.load(in);
            Enumeration<?> en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                if(key.contains(GMATEDHOST)){
                    String host = props.getProperty (key);
                    String[] result = host.split(SPITSYMBOL);
                    String ip = result[0];
                    int port = Integer.parseInt(result[1]);
                    hostMap.put(ip, port);
                    logger.info("读取属性配置文件IP为: "+ip+"端口为 :"+port);
                }
                if(COLLECTEDPERIOD.equals(key)){
                    periodTime = Integer.parseInt(props.getProperty (key));
                }
            }
        } catch (IOException e) {
            logger.error("加载配置文件出现错误!");
        } catch(ArrayIndexOutOfBoundsException e){
            logger.error("读取配置文件属性出错!");
        } 
    }

}
