package com.cnsuning.gslb.xml.saxparse;

import java.util.List;

import com.cnsuning.gslb.node.model.ExtraData;
import com.cnsuning.gslb.node.model.ExtraElement;

public class ExtraDataInfoHandler {
    
    /* EXTRA_DATA related info.*/
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static ExtraData extraData;
    private static ExtraData currentExtraData;
    
    public ExtraData getExtraData() {
        return extraData;
    }
    
    public ExtraDataInfoHandler() {
        extraData = new ExtraData();
    }
    
    /* Initial the extra data in metric*/
    public void initExtraDataInfo(String qName) {
        if(EXTRA_DATA.equals(qName)){
            currentExtraData = new ExtraData();
        }
    }
    
    public void setInfoToExtraData(List<ExtraElement> extraElement, String qName) {
        if(EXTRA_DATA.equals(qName)){
            currentExtraData.setExtra_element(extraElement);
            extraData = currentExtraData;
            currentExtraData = null;
        }
    }
}
