package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.model.ExtraElement;


public class ExtraElementInfoHandler {
    /* EXTRA_ELEMENT related info.*/
    private static final String EXTRA_ELEMENT = "EXTRA_ELEMENT";
    private static List<ExtraElement> extraElement;
    private static ExtraElement currentExtraElement;
    private static final  String EXTRA_ELEMENT_NAME = "NAME";
    private static final  String EXTRA_ELEMENT_VAL = "VAL";
    
    public List<ExtraElement> getExtraElement() {
        return extraElement;
    }
    
    public ExtraElementInfoHandler() {
        extraElement = new ArrayList<ExtraElement>();
    }
    
    /* Initial the extra element in metric*/
    public void initExtraElementInfo(String qName, Attributes attributes) {
        if(EXTRA_ELEMENT.equals(qName)){
            currentExtraElement = new ExtraElement();
            currentExtraElement.setName(attributes.getValue(EXTRA_ELEMENT_NAME));
            currentExtraElement.setVal(attributes.getValue(EXTRA_ELEMENT_VAL));
        }
    }
    
    public void setInfoToExtraElement(String qName) {
        if(EXTRA_ELEMENT.equals(qName)){
            extraElement.add(currentExtraElement);
            currentExtraElement = null;
        }
    }
}
