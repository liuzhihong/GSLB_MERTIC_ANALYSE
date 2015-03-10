package com.suning.gslb.xml.saxparse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.suning.gslb.metric.util.ClientSocket;

public class GmatedDataParse extends DefaultHandler {
    
    private static final String EXTRA_ELEMENT = "EXTRA_ELEMENT";
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static final String METRIC = "METRIC";
    private static final String HOST = "HOST";
    private static final String CLUSTER = "CLUSTER";
    
    private static ClusterInfoHandler clusterInfoService;
    private static HostInfoHandler hostInfoService;
    private static MetricInfoHandler metricInfoService;
    private static ExtraDataInfoHandler extraDataInfoService;
    private static ExtraElementInfoHandler extraElementInfoService;
    
    private static final Logger logger = Logger.getLogger(GmatedDataParse.class); 
    
    private static GmatedDataParse inst = new GmatedDataParse();
    
    public static GmatedDataParse getInstance() {
        return inst;
    }
    
    public void startParse(String nodeAddress, int nodePort) {
        logger.info("当前解析节点IP为: "+nodeAddress+",端口为:"+nodePort);
        try {
            parse(nodeAddress, nodePort);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }
    
    public void parse(String node_address, int node_port) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        SAXParser parser = parserFactory.newSAXParser();
        InputStream is = ClientSocket.getClientSocket(node_address, node_port).getInputStream();
        /*int repeatTimes = 0;
        while(is == null && repeatTimes < 3){
            repeatTimes++;
            is = new Socket(node_address, node_port).getInputStream();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }*/
        parseDataFromGmated(parser, is);
    }
    
    private void parseDataFromGmated(SAXParser parser, InputStream is)
            throws IOException, SAXException {
        parser.parse(is, this);
    }

    public void startDocument() throws SAXException {
        logger.info("正常启动数据流解析，开始时间为: "+new Date());
        clusterInfoService = new ClusterInfoHandler();
        hostInfoService = new HostInfoHandler();
        metricInfoService = new MetricInfoHandler();
        extraDataInfoService = new ExtraDataInfoHandler();
        extraElementInfoService = new ExtraElementInfoHandler();
    }
    
    public void endDocument() throws SAXException {
        logger.info("流文件解析结束，结束时间为: "+new Date());
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        logger.info("当前解析节点元素为: "+qName);
        if(CLUSTER.equals(qName)){
            clusterInfoService.initClusterInfo(attributes);
        }
        if(HOST.equals(qName)){
            hostInfoService.initHostInfo(attributes);
        }
        if(METRIC.equals(qName)){
            metricInfoService.initMetricInfo(attributes);
        }
        if(EXTRA_DATA.equals(qName)){
            extraDataInfoService.initExtraDataInfo();
        }
        if(EXTRA_ELEMENT.equals(qName)){
            extraElementInfoService.initExtraElementInfo(attributes);
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        logger.info("当前元素对象解析完成，结束标签为: </"+qName+">");
        if(EXTRA_ELEMENT.equals(qName)){
            extraElementInfoService.setInfoToExtraElement();
        }
        if(EXTRA_DATA.equals(qName)){
            extraDataInfoService.setInfoToExtraData(extraElementInfoService.getExtraElement());
            extraElementInfoService.initExtraElementList();
        }
        if(METRIC.equals(qName)){
            metricInfoService.setInfoToMetric(extraDataInfoService.getExtraData());
        }
        if(HOST.equals(qName)){
            hostInfoService.setInfoToHost(metricInfoService.getMetric());
            metricInfoService.initMetricList();;
        }
        if(CLUSTER.equals(qName)){
            clusterInfoService.setInfoToCluster(hostInfoService.getHost());
            hostInfoService.initHostList();
        }
    }
}