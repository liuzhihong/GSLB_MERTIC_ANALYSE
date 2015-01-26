package com.cnsuning.prototype;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cnsuning.prototype.xml.nodes.Cluster;
import com.cnsuning.prototype.xml.nodes.ExtraData;
import com.cnsuning.prototype.xml.nodes.ExtraElement;
import com.cnsuning.prototype.xml.nodes.Host;
import com.cnsuning.prototype.xml.nodes.Metric;

public class ClusterXmlParse extends DefaultHandler {
    
    /* Cluster related info.*/
    private static List<Cluster> cluster;
    private static Cluster currentCluster;
    private static final String CLUSTER = "CLUSTER";
    private static final String CLUSTER_NAME = "NAME";
    private static final String CLUSTER_LOCALTIME = "LOCALTIME";
    private static final String CLUSTER_OWNER = "OWNER";
    private static final String CLUSTER_LATLONG = "LATLONG";
    private static final String CLUSTER_URL = "URL";
    
    /* Host related info.*/
    private static List<Host> host;
    private static Host currentHost;
    private static final String HOST = "HOST";
    private static final  String HOST_NAME = "NAME";
    private static final  String HOST_IP = "IP";
    private static final  String HOST_REPORTED = "REPORTED";
    private static final  String HOST_TN = "TN";
    private static final  String HOST_TMAX = "TMAX";
    private static final  String HOST_DMAX = "DMAX";
    private static final  String HOST_LOCATION = "LOCATION";
    private static final  String HOST_GMOND_STARTED = "GMOND_STARTED";
    private static final  String HOST_TAGS = "TAGS";
    
    /* Metric related info.*/
    private static List<Metric> metric;
    private static Metric currentMetric;
    private static final String METRIC = "METRIC";
    private static final  String METRIC_NAME = "NAME";
    private static final  String METRIC_VAL = "VAL";
    private static final  String METRIC_TYPE = "TYPE";
    private static final  String METRIC_UNITS = "UNITS";
    private static final  String METRIC_TN = "TN";
    private static final  String METRIC_TMAX = "TMAX";
    private static final  String METRIC_DMAX = "DMAX";
    private static final  String METRIC_SLOPE = "SLOPE";
    private static final  String METRIC_SOURCE = "SOURCE";

    /* EXTRA_DATA related info.*/
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static ExtraData extraData;
    private static ExtraData currentExtraData;
    
    /* EXTRA_ELEMENT related info.*/
    private static final String EXTRA_ELEMENT = "EXTRA_ELEMENT";
    private static List<ExtraElement> extraElement;
    private static ExtraElement currentExtraElement;
    private static final  String EXTRA_ELEMENT_NAME = "NAME";
    private static final  String EXTRA_ELEMENT_VAL = "VAL";
    
    private Stack<InputStream> stack = new Stack<InputStream>();
    private Locator locator;


    public static void main(String[] args) {
        ClusterXmlParse p = new ClusterXmlParse();
        try {
            p.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        parserFactory.setValidating(true);
        SAXParser parser = parserFactory.newSAXParser();
        InputStream is = new Socket("192.168.192.137", 8651).getInputStream();
        if (is.available() > 0) {
            stack.push(is);
        }
        parser.parse(is, this);
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }
    
    public void startDocument() throws SAXException {
        cluster = new ArrayList<Cluster>();
        host = new ArrayList<Host>();
        metric = new ArrayList<Metric>();
        extraData = new ExtraData();
    }
    
    public void endDocument() throws SAXException {
        if (!stack.empty()) {
            InputStream is = stack.pop();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new SAXException(e.getMessage());
                }
            }
        }
        System.out.println("EXTRA_ELEMENT = "+ cluster.get(0).getHost().get(0).getMetric().get(0).getExtra_data().getExtra_element().get(0).getName());
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        /* Initial the cluster's data */
        if(CLUSTER.equals(qName)){
            currentCluster = new Cluster();
            currentCluster.setName(attributes.getValue(CLUSTER_NAME));
            currentCluster.setLatlong(attributes.getValue(CLUSTER_LATLONG));
            currentCluster.setLocaltime(attributes.getValue(CLUSTER_LOCALTIME));
            currentCluster.setOwner(attributes.getValue(CLUSTER_OWNER));
            currentCluster.setUrl(attributes.getValue(CLUSTER_URL));
//            System.out.println("clusterName = " + attributes.getValue(CLUSTER_NAME));
        }
        
        /* Initial the host's data */
        if(HOST.equals(qName)){
            currentHost = new Host();
            currentHost.setName(attributes.getValue(HOST_NAME));
            currentHost.setIp(attributes.getValue(HOST_IP));
            currentHost.setReported(attributes.getValue(HOST_REPORTED));
            currentHost.setTn(attributes.getValue(HOST_TN));
            currentHost.setTmax(attributes.getValue(HOST_TMAX));
            currentHost.setDmax(attributes.getValue(HOST_DMAX));
            currentHost.setLocation(attributes.getValue(HOST_LOCATION));
            currentHost.setGmond_stated(attributes.getValue(HOST_GMOND_STARTED));
            currentHost.setTags(attributes.getValue(HOST_TAGS));
//            System.out.println("hostName = " + attributes.getValue(HOST_NAME));
        }
        
        /* Initial the metric's data */
        if(METRIC.equals(qName)){
            currentMetric = new Metric();
            currentMetric.setName(attributes.getValue(METRIC_NAME));
            currentMetric.setVal(attributes.getValue(METRIC_VAL));
            currentMetric.setType(attributes.getValue(METRIC_TYPE));
            currentMetric.setUnits(attributes.getValue(METRIC_UNITS));
            currentMetric.setTn(attributes.getValue(METRIC_TN));
            currentMetric.setTmax(attributes.getValue(METRIC_TMAX));
            currentMetric.setDmax(attributes.getValue(METRIC_DMAX));
            currentMetric.setSlope(attributes.getValue(METRIC_SLOPE));
            currentMetric.setSource(attributes.getValue(METRIC_SOURCE));
//            System.out.println("metric = " + attributes.getValue(METRIC_NAME));
        }
        
        /* Initial the extra data in metric*/
        if(EXTRA_DATA.equals(qName)){
            currentExtraData = new ExtraData();
            extraElement = new ArrayList<ExtraElement>();
        }
        
        /* Initial the extra element in metric*/
        if(EXTRA_ELEMENT.equals(qName)){
            currentExtraElement = new ExtraElement();
            currentExtraElement.setName(attributes.getValue(EXTRA_ELEMENT_NAME));
            currentExtraElement.setVal(attributes.getValue(EXTRA_ELEMENT_VAL));
        }
        
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(EXTRA_ELEMENT.equals(qName)){
            extraElement.add(currentExtraElement);
            currentExtraElement = null;
        }
        if(EXTRA_DATA.equals(qName)){
            currentExtraData.setExtra_element(extraElement);
            extraData = currentExtraData;
            currentExtraData = null;
        }
        if(METRIC.equals(qName)){
            currentMetric.setExtra_data(extraData);
            metric.add(currentMetric);
            currentMetric = null;
        }
        if(HOST.equals(qName)){
            currentHost.setMetric(metric);
            host.add(currentHost);
            currentHost = null;
        }
        if(CLUSTER.equals(qName)){
            currentCluster.setHost(host);
            cluster.add(currentCluster);
            currentCluster = null;
        }
    }
}