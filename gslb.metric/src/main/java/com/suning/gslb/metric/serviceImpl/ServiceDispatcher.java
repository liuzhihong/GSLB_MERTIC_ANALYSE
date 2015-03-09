package com.suning.gslb.metric.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.suning.gslb.metric.service.AlarmThresholdBeanService;
import com.suning.gslb.metric.service.BaseAlarmBeanService;
import com.suning.gslb.metric.service.BaseAlarmRecorderBeanService;
import com.suning.gslb.metric.service.CDNNodeAlarmBeanService;
import com.suning.gslb.metric.service.ForbiddenMetricBeanService;
import com.suning.gslb.metric.service.MachineAlarmBeanService;
import com.suning.gslb.metric.service.MachineAlarmRecorderBeanService;
import com.suning.gslb.metric.service.MachineBeanService;
import com.suning.gslb.node.biz.model.BaseAlarmModel;
import com.suning.gslb.node.biz.model.BaseAlarmRecorderModel;
import com.suning.gslb.node.biz.model.CDNNodeAlarmModel;
import com.suning.gslb.node.biz.model.ForbiddenMetricModel;
import com.suning.gslb.node.biz.model.MachineAlarmModel;
import com.suning.gslb.node.biz.model.MachineAlarmRecorderModel;
import com.suning.gslb.node.biz.model.MachineModel;
import com.suning.gslb.node.biz.model.MetricThresholdModel;
import com.suning.gslb.node.data.model.ClusterEntity;
import com.suning.gslb.node.data.model.HostEntity;
import com.suning.gslb.node.data.model.MetricEntity;



/**
 * 
 * @author Jeremy Lv
 *
 */
public class ServiceDispatcher {
    
    private static AlarmThresholdBeanService alarmThresholdBeanService;
    private static MachineBeanService machineBeanService;
    private static MachineAlarmBeanService machineAlarmBeanService;
    private static BaseAlarmBeanService baseAlarmBeanService;
    private static BaseAlarmRecorderBeanService baseAlarmRecorderBeanService;
    private static MachineAlarmRecorderBeanService machineAlarmRecorderBeanService;
    private static CDNNodeAlarmBeanService cdnNodeAlarmBeanService;
    private static ForbiddenMetricBeanService forbiddenMetricBeanService;
    
    //The map used to store the threshold data in the tb_table_dictionary.
    private static HashMap<String,Double> alarmThresholdMap;
    
    private static List<MachineAlarmModel> currentDeviceAlarmEntityList;
    private static List<MachineAlarmModel> storedDeviceAlarmEntityList;
    private static List<CDNNodeAlarmModel> currentNodeAlarmEntityList;
    private static List<CDNNodeAlarmModel> storedNodeAlarmEntityList;
    
    private static List<MetricThresholdModel> alarmThresholdEntityList;
    
    private static List<ForbiddenMetricModel> forbiddenMetricList;
    
    private static List<MachineModel> hostEntityList;
    
    private static ServiceDispatcher inst = new ServiceDispatcher();
    
    public static ServiceDispatcher getInstance(ApplicationContext context) {
        alarmThresholdBeanService = (AlarmThresholdBeanService) context.getBean("alarmThresholdBeanService");
        machineBeanService = (MachineBeanService) context.getBean("machineBeanService");
        machineAlarmBeanService = (MachineAlarmBeanService) context.getBean("machineAlarmBeanService");
        baseAlarmBeanService = (BaseAlarmBeanService) context.getBean("baseAlarmBeanService");
        baseAlarmRecorderBeanService = (BaseAlarmRecorderBeanService) context.getBean("baseAlarmRecorderBeanService");
        machineAlarmRecorderBeanService = (MachineAlarmRecorderBeanService) context.getBean("machineAlarmRecorderBeanService");
        cdnNodeAlarmBeanService = (CDNNodeAlarmBeanService) context.getBean("cdnNodeAlarmBeanService");
        forbiddenMetricBeanService = (ForbiddenMetricBeanService) context.getBean("forbiddenMetricBeanService");
        return inst;
    }
    
    public ServiceDispatcher() {
        currentDeviceAlarmEntityList = new ArrayList<MachineAlarmModel>();
        currentNodeAlarmEntityList = new ArrayList<CDNNodeAlarmModel>();
    }
    
    /**
     * Initialize the static global variable, such as 
     * alarmThresholdMap, currentAlarmEntityList, storedAlarmEntityList.
     * @param context
     * @param currentClusterEntityList 
     */
    public void initServiceDispatcher(List<ClusterEntity> currentClusterEntityList) {
        initAlarmThresHoldMap();
        hostEntityList = getAllHostEntity();
        //查找当前机器告警表里面的记录
        storedDeviceAlarmEntityList = getAllStoredDeviceAlarmEntity();
        //查找当前节点表里面的告警记录
        storedNodeAlarmEntityList = getAllStoredNodeAlarmEntity();
        //查找所有的禁用指标记录
        forbiddenMetricList = getAllForbiddenMetricEntity();
        if(isEntityListEmpty()){
            return;
        }
        for(ClusterEntity clusterInfo : currentClusterEntityList){
            List<HostEntity> hostInfoList = clusterInfo.getHost();
            for(HostEntity hostInfo : hostInfoList){
                List<MetricEntity> metrics = hostInfo.getMetric();
                HashMap<String,Double> illegalMetricMap = getIllegalMetricElement(metrics);
                //根据非法的告警指标来初始化机器告警表
                initDeviceAlarmEntity(illegalMetricMap, hostInfo);
            }
        }
        
    }
    
    private List<ForbiddenMetricModel> getAllForbiddenMetricEntity() {
        return forbiddenMetricBeanService.getAllForbiddenMetric();
    }

    private List<MachineAlarmModel> getAllStoredDeviceAlarmEntity() {
        List<MachineAlarmModel> allStoredDeviceAlarmEntityList = new ArrayList<MachineAlarmModel>();
        List<MachineAlarmModel> deviceAlarmEntityList = machineAlarmBeanService.getAllDeviceAlarmEntity();
        Iterator<MachineAlarmModel> deviceAlarmEntityIterator = deviceAlarmEntityList.iterator();
        while(deviceAlarmEntityIterator.hasNext()){
            MachineAlarmModel deviceAlarmEntity = deviceAlarmEntityIterator.next();
            BaseAlarmModel baseAlarmEntity = getBaseAlarmEntity(deviceAlarmEntity.getBaseId());
            deviceAlarmEntity.setBaseAlarmEntity(baseAlarmEntity);
            allStoredDeviceAlarmEntityList.add(deviceAlarmEntity);
        }
        return allStoredDeviceAlarmEntityList;
    }
    
    private BaseAlarmModel getBaseAlarmEntity(int based_id) {
        return baseAlarmBeanService.getBaseAlarmEntity(based_id);
    }

    private List<CDNNodeAlarmModel> getAllStoredNodeAlarmEntity() {
        List<CDNNodeAlarmModel> allStoredDeviceAlarmEntityList = new ArrayList<CDNNodeAlarmModel>();
        List<CDNNodeAlarmModel> nodeAlarmEntityList = cdnNodeAlarmBeanService.getAllNodeAlarmEntity();
        Iterator<CDNNodeAlarmModel> nodeAlarmEntityIterator = nodeAlarmEntityList.iterator();
        while(nodeAlarmEntityIterator.hasNext()){
            CDNNodeAlarmModel nodeAlarmEntity = nodeAlarmEntityIterator.next();
            BaseAlarmModel baseAlarmEntity = getBaseAlarmEntity(nodeAlarmEntity.getBaseId());
            nodeAlarmEntity.setBaseAlarmEntity(baseAlarmEntity);
            allStoredDeviceAlarmEntityList.add(nodeAlarmEntity);
        }
        return allStoredDeviceAlarmEntityList;
    }

    private boolean isEntityListEmpty() {
        if(hostEntityList == null || alarmThresholdEntityList == null){
            return true;
        }
        return false;
    }

    private List<MachineModel> getAllHostEntity() {
        return machineBeanService.getAllHostEntity();
    }

    /**
     * Initialize the tb_table table only if 
     * the tb_table table is empty.
     * @param context
     */
    public void initAlarmTable() {
        if(isEntityListEmpty()){
            return;
        }
        if(storedDeviceAlarmEntityList.size() == 0){
            for(MachineAlarmModel machineModel : currentDeviceAlarmEntityList){
                boolean flag = true;
                if(forbiddenMetricList.size() > 0){
                    for(ForbiddenMetricModel forbiddenMetric : forbiddenMetricList){
                        if(machineModel.getBaseAlarmEntity().getParamId()==forbiddenMetric.getParamId()
                                &&machineModel.getDeviceId()==forbiddenMetric.getDeviceId()){
                            flag &= false;
                            break;
                        }
                    }
                }
                if(flag){
                    BaseAlarmModel baseAlarmModel = new BaseAlarmModel();
                    String alarmContent = getAlarmMessage(machineModel);
                    baseAlarmModel.setAlarmContent(alarmContent);
                    baseAlarmModel.setParamId(machineModel.getBaseAlarmEntity().getParamId());
                    baseAlarmModel.setCurrentValue(machineModel.getBaseAlarmEntity().getCurrentValue());
                    baseAlarmModel.setStartTime(new Date());
                    insertAlarmEntityAndGetBaseId(baseAlarmModel);
                    int baseId = baseAlarmModel.getBaseId();
                    //在这一步当前机器告警列表里面的数据有了变化，部分记录有了base_id，表示存储数据库
                    machineModel.setBaseId(baseId);
                    insertDeviceAlarmTable(machineModel);
                }
            }
        }
    }
    

    private void insertDeviceAlarmTable(MachineAlarmModel currentDeviceAlarmEntity) {
        machineAlarmBeanService.insertDeviceAlarmEntity(currentDeviceAlarmEntity);
        
    }

    private void insertAlarmEntityAndGetBaseId(BaseAlarmModel baseAlarmEntity) {
        baseAlarmBeanService.insertAlarmEntityAndGetBaseId(baseAlarmEntity);
    }

    /**
     * Select the current alarm messages.
     * @param context 
     * @param currentAlarmEntity
     * @return
     */
    private String getAlarmMessage(MachineAlarmModel currentDeviceAlarmEntity) {
        int param_Id = currentDeviceAlarmEntity.getBaseAlarmEntity().getParamId();
        int device_Id = currentDeviceAlarmEntity.getDeviceId();
        double current_Value = currentDeviceAlarmEntity.getBaseAlarmEntity().getCurrentValue();
        String param_Name = getParamName(param_Id);
        String device_Name = getDeviceName(device_Id);
        double threshold = alarmThresholdMap.get(param_Name);
        String alarm_Message = "The current value of " + param_Name 
                + " in the host " + device_Name 
                + " is " + current_Value 
                + " which is above " + threshold 
                + ", please notice it.";
        return alarm_Message;
    }

    private String getDeviceName(int device_Id) {
        Iterator<MachineModel> hostEntityIterator = hostEntityList.iterator();
        while(hostEntityIterator.hasNext()){
            MachineModel hostEntity = hostEntityIterator.next();
            if(device_Id == hostEntity.getDeviceId()){
                return hostEntity.getDeviceName();
            }
        }
        return "";
    }

    private String getParamName(int param_Id) {
        Iterator<MetricThresholdModel> alarmThresholdEntityIterator = alarmThresholdEntityList.iterator();
        while(alarmThresholdEntityIterator.hasNext()){
            MetricThresholdModel alarmThresholdEntity = alarmThresholdEntityIterator.next();
            if(param_Id == alarmThresholdEntity.getParamId()){
                return alarmThresholdEntity.getParamName();
            }
        }
        return "";
    }

    /**
     * 
     * 功能描述: 查找现有的机器告警表（store）当中是否有当前（current）告警列表里面的记录，
     * 有：不做任何操作，上一步中数据已经同步更新
     * 无：在基础告警表中增加一条记录，机器告警表中增加一条记录，原子操作
     *
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void insertItemsToAlarmTable() {
        if(isEntityListEmpty()){
            return;
        }
        for(MachineAlarmModel currAlarmModel : currentDeviceAlarmEntityList){
            boolean flag = true;
            for(MachineAlarmModel storeAlarmModel : storedDeviceAlarmEntityList){
                if(isExistAlarmRecord(currAlarmModel, storeAlarmModel)){
                    flag &= false;
                    break;
                }
            }
            if(flag){
                BaseAlarmModel baseAlarmEntity = new BaseAlarmModel();
                String alarmContent = getAlarmMessage(currAlarmModel);
                baseAlarmEntity.setAlarmContent(alarmContent);
                baseAlarmEntity.setCurrentValue(currAlarmModel.getBaseAlarmEntity().getCurrentValue());
                baseAlarmEntity.setParamId(currAlarmModel.getBaseAlarmEntity().getParamId());
                baseAlarmEntity.setStartTime(new Date());
                insertAlarmEntityAndGetBaseId(baseAlarmEntity);
                int baseId = baseAlarmEntity.getBaseId();
                currAlarmModel.setBaseId(baseId);
                insertDeviceAlarmTable(currAlarmModel); 
            }
        }
    }

    /**
     * Update the tb_alarm table if the items need to be updated.
     * @param context
     */
    public void updateAlarmTable() {
        if(isEntityListEmpty()){
            return;
        }
        for(MachineAlarmModel storeMachineAlarm : storedDeviceAlarmEntityList){
            updateBaseAlarmTable(storeMachineAlarm);
        }
    }

    /**
     * Update the stored alarm entity.
     * 
     * Case 1: If the record is still alarmed, update the 
     * current_value and alarm_content.
     */
    private void updateBaseAlarmTable(MachineAlarmModel storedDeviceAlarmEntity) {
        boolean flag = true;//设置一个状态指标
        MachineAlarmModel currentAlarmModel = null;
        for(MachineAlarmModel alarmModel : currentDeviceAlarmEntityList){
            if(isExistAlarmRecord(storedDeviceAlarmEntity, alarmModel)){
                flag &= false;
                currentAlarmModel = alarmModel;
                break;
            }
        }
        if(flag){
            /**
             * 如果检索出来的记录在当前的告警记录列表里面循环一遍都未找到，则表示警报解除，需要做如下:
             * 1.先删除机器告警表里面的该条记录
             * 2.然后删除基础告警表里面的记录
             * 3.把该条记录插入到基础告警历史表当中
             * 4.把该条记录插入到机器告警历史表当中
             * 
             */
            deleteDataInDeviceAlarmTable(storedDeviceAlarmEntity);
            deleteDataInBaseAlarmTable(storedDeviceAlarmEntity);
            int baseId = insertDataIntoBaseAlarmRecorderTable(storedDeviceAlarmEntity);
            storedDeviceAlarmEntity.setBaseId(baseId);
            insertDataIntoDeviceAlarmRecorderTable(storedDeviceAlarmEntity);
            
        }else{
            String alarmContent = getAlarmMessage(currentAlarmModel);
            BaseAlarmModel baseAlarmEntity = currentAlarmModel.getBaseAlarmEntity();
            int base_id = storedDeviceAlarmEntity.getBaseId();
            baseAlarmEntity.setBaseId(base_id);
            baseAlarmEntity.setAlarmContent(alarmContent);
            updateDataInAlarmTable(baseAlarmEntity);
        }
    }

    private void insertDataIntoDeviceAlarmRecorderTable(MachineAlarmModel storedDeviceAlarmEntity) {
        MachineAlarmRecorderModel deviceAlarmRecorderEntity = new MachineAlarmRecorderModel();
        deviceAlarmRecorderEntity.setBaseId(storedDeviceAlarmEntity.getBaseId());
        deviceAlarmRecorderEntity.setDeviceId(storedDeviceAlarmEntity.getDeviceId());
        machineAlarmRecorderBeanService.insertDeviceAlarmEntity(deviceAlarmRecorderEntity);
    }

    private int insertDataIntoBaseAlarmRecorderTable(MachineAlarmModel storedDeviceAlarmEntity) {
        BaseAlarmModel baseAlarmEntity = storedDeviceAlarmEntity.getBaseAlarmEntity();
        BaseAlarmRecorderModel baseAlarmRecorderEntity = new BaseAlarmRecorderModel();
        baseAlarmRecorderEntity.setAlarmContent(baseAlarmEntity.getAlarmContent());
        baseAlarmRecorderEntity.setCurrentValue(baseAlarmEntity.getCurrentValue());
        baseAlarmRecorderEntity.setParamId(baseAlarmEntity.getParamId());
        baseAlarmRecorderEntity.setStartTime(baseAlarmEntity.getStartTime());
        baseAlarmRecorderEntity.setEndTime(new Date());
        baseAlarmRecorderBeanService.insertAlarmEntityAndGetBaseId(baseAlarmRecorderEntity);
        return baseAlarmRecorderEntity.getBaseId();
    }

    private void deleteDataInBaseAlarmTable(MachineAlarmModel storedDeviceAlarmEntity) {
        BaseAlarmModel baseAlarmEntity = storedDeviceAlarmEntity.getBaseAlarmEntity();
        baseAlarmBeanService.delete(baseAlarmEntity);
    }

    private void deleteDataInDeviceAlarmTable(MachineAlarmModel storedDeviceAlarmEntity) {
        machineAlarmBeanService.delete(storedDeviceAlarmEntity);
    }

    /**
     * Check whether the current_value in the tb_alarm need to be updated.
     * @param context
     * @param storedDeviceAlarmEntity
     * @param currentDeviceAlarmEntity
     * @return
     */
    private boolean isExistAlarmRecord(MachineAlarmModel storedDeviceAlarmEntity,
            MachineAlarmModel currentDeviceAlarmEntity) {
        if(storedDeviceAlarmEntity.getBaseAlarmEntity().getParamId() == currentDeviceAlarmEntity.getBaseAlarmEntity().getParamId()
                && storedDeviceAlarmEntity.getDeviceId() == currentDeviceAlarmEntity.getDeviceId()){
            return true;
        }
        return false;
    }

    /**
     * Get all of the alarm data collected from the Gmated 
     * machine and stored them to a List.
     * @param alarmEntityList 
     * @param illegalMetricMap
     * @param hostInfo
     */
    private void initDeviceAlarmEntity(HashMap<String, Double> illegalMetricMap,
            HostEntity hostInfo) {
        //if the illegalMetricMap is null, then return and will not update the tb_alarm table.
        if(illegalMetricMap.size()==0){
            return;
        }
        
        MachineModel currentHostEntity = null;
        for(MachineModel host : hostEntityList){
            if(hostInfo.getName().equals(host.getDeviceName())&&hostInfo.getIp().equals(host.getDeviceIp())){
                currentHostEntity = host;
                break;
            }
        }
        
        for(Map.Entry<String, Double> entry : illegalMetricMap.entrySet()){
            MachineAlarmModel deviceAlarmEntity = new MachineAlarmModel();
            BaseAlarmModel baseAlarmEntity = new BaseAlarmModel();
            String paramName = entry.getKey();
            double alarmValue = entry.getValue();
            for(MetricThresholdModel metricModel : alarmThresholdEntityList){
                if(paramName.equals(metricModel.getParamName())){
                    int paramId = metricModel.getParamId();
                    baseAlarmEntity.setParamId(paramId);
                    baseAlarmEntity.setCurrentValue(alarmValue);
                    break;
                }
            }
            if(currentHostEntity != null){
                int deviceId = currentHostEntity.getDeviceId();
                deviceAlarmEntity.setBaseAlarmEntity(baseAlarmEntity);
                deviceAlarmEntity.setDeviceId(deviceId);
                currentDeviceAlarmEntityList.add(deviceAlarmEntity);
            }
        }
        
    }

    
    /**
     * 
     * 功能描述: 根据数据库表中设定的阈值来比较采集的指标值
     *
     * @param metricInfoList
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private HashMap<String, Double> getIllegalMetricElement(List<MetricEntity> metricInfoList) {
        HashMap<String, Double> illegalMetricMap = new HashMap<String, Double>();
        if(alarmThresholdMap.isEmpty()){
            return illegalMetricMap;
        }
        for(MetricEntity metricEntity : metricInfoList){
            String metricName = metricEntity.getName();
            if(alarmThresholdMap.containsKey(metricName)){
                double value = Double.parseDouble(metricEntity.getVal());
                if(value > alarmThresholdMap.get(metricName)){
                    illegalMetricMap.put(metricName, value);
                }
            }
        }
        return illegalMetricMap;        
    }
    
    /**
     * get all kinds of the threshold from the tb_data_dictionary.
     * @param context
     * @return
     */
    private HashMap<String,Double> getALLAlarmThresHold() {
        HashMap<String,Double> alarmThresholdMap = new HashMap<String,Double>();
        alarmThresholdEntityList = getAllAlarmThreshold();
        if(alarmThresholdEntityList == null){
            return null;
        }
        Iterator<MetricThresholdModel> alarmThresholdEntityIterator = alarmThresholdEntityList.iterator();
        while(alarmThresholdEntityIterator.hasNext()){
            MetricThresholdModel alarmThresholdEntity = alarmThresholdEntityIterator.next();
            alarmThresholdMap.put(alarmThresholdEntity.getParamName(), alarmThresholdEntity.getAlarmValue());
        }
        return alarmThresholdMap;
    }
    
    private List<MetricThresholdModel> getAllAlarmThreshold() {
        List<MetricThresholdModel> allAlarmThreshold = alarmThresholdBeanService.getAllAlarmThreshold();
        return allAlarmThreshold;
    }
    
    
    private void updateDataInAlarmTable(BaseAlarmModel baseAlarmEntity) {
        baseAlarmBeanService.updateAlarmTable(baseAlarmEntity);
    }
    

    /**
     * Store all kinds of threshold to a map.
     * @param context
     */
    public void initAlarmThresHoldMap() {
        alarmThresholdMap = getALLAlarmThresHold();
    }
    
    public void calculate() {
        //TODO need to calculate the current QPS.
        calculateCurrentQPS();
        //TODO need to calculate the exchanged QPS.
        calculateExchangedQPS();
    }
    
    public void calculateExchangedQPS(){
        
    }
    
    public void calculateCurrentQPS() {
        
    }

}
