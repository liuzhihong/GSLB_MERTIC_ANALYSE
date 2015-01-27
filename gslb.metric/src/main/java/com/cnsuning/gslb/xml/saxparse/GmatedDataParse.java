package com.cnsuning.gslb.xml.saxparse;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class GmatedDataParse extends DefaultHandler {
    
    private static ClusterInfoService clusterInfoService;
    private static HostInfoService hostInfoService;
    private static MetricInfoService metricInfoService;
    private static ExtraDataInfoService extraDataInfoService;
    private static ExtraElementInfoService extraElementInfoService;
    private static final String NODE_ADDRESS = "192.168.192.137";
    private static final int NODE_PORT = 8651;
    
//    private Stack<InputStream> stack = new Stack<InputStream>();

    public void getDataFromGmated() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        SAXParser parser = parserFactory.newSAXParser();
        InputStream is = new Socket(NODE_ADDRESS, NODE_PORT).getInputStream();
        parseDataFromGmated(parser, is);
    }

    private void parseDataFromGmated(SAXParser parser, InputStream is)
            throws IOException, SAXException {
//        if (is.available() > 0) {
//            stack.push(is);
//        }
        parser.parse(is, this);
    }

    public void startDocument() throws SAXException {
        clusterInfoService = new ClusterInfoService();
        hostInfoService = new HostInfoService();
        metricInfoService = new MetricInfoService();
        extraDataInfoService = new ExtraDataInfoService();
        extraElementInfoService = new ExtraElementInfoService();
    }
    
    public void endDocument() throws SAXException {
//        isStackEmpty();
        System.out.println("EXTRA_ELEMENT = "+ clusterInfoService.getCluster().get(0).getHost().get(0).getMetric().get(0).getExtra_data().getExtra_element().get(0).getName());
    }

//    private void isStackEmpty() throws SAXException {
//        if (!stack.empty()) {
//            InputStream is = stack.pop();
//            if (is != null) {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    throw new SAXException(e.getMessage());
//                }
//            }
//        }
//    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        clusterInfoService.initClusterInfo(qName, attributes);
        hostInfoService.initHostInfo(qName, attributes);
        metricInfoService.initMetricInfo(qName, attributes);
        extraDataInfoService.initExtraDataInfo(qName);
        extraElementInfoService.initExtraElementInfo(qName, attributes);
        
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        extraElementInfoService.setInfoToExtraElement(qName);
        extraDataInfoService.setInfoToExtraData(extraElementInfoService.getExtraElement(), qName);
        metricInfoService.setInfoToMetric(extraDataInfoService.getExtraData(), qName);
        hostInfoService.setInfoToHost(metricInfoService.getMetric(), qName);
        clusterInfoService.setInfoToCluster(hostInfoService.getHost(), qName);
    }
}