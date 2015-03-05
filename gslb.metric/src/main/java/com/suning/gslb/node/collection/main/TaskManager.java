package com.suning.gslb.node.collection.main;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cnsuning.gslb.node.model.ClusterEntity;
import com.cnsuning.gslb.xml.saxparse.ClusterInfoHandler;
import com.cnsuning.gslb.xml.saxparse.GmatedDataParse;
import com.suning.gslb.metric.service.ServiceDispatcher;

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
        Iterator<Entry<String, Integer>> it = hostMap.entrySet().iterator();
        while(it.hasNext()){
            Entry<String, Integer> entry = it.next();
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
        serviceDispacher.initServiceDispatcher(currentClusterEntityList);

      
        //Only executed when the tb_device_alarm table is empty.
        serviceDispacher.initAlarmTable();

        //Only executed when the tb_device_alarm table need to be updated.
        serviceDispacher.updateAlarmTable();
        //Only executed when there's new items need to be inserted to the tb_device_alarm table
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
                    System.out.println(ip+" "+port);
                }
                if(COLLECTEDPERIOD.equals(key)){
                    periodTime = Integer.parseInt(props.getProperty (key));
                }
            }
        } catch (IOException e) {
            //logger.error("加载属性过程中发生错误");
        } catch(ArrayIndexOutOfBoundsException e){
            //logger.error("请在config.properties文件中设置正确的ip和port值");
        } 
    }

}
