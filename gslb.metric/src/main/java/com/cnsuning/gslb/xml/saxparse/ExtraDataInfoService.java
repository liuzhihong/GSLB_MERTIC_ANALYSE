package com.cnsuning.gslb.xml.saxparse;

import java.util.List;

import com.cnsuning.gslb.node.model.ExtraDataInfo;
import com.cnsuning.gslb.node.model.ExtraElementInfo;

public class ExtraDataInfoService {
    
    /* EXTRA_DATA related info.*/
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static ExtraDataInfo extraData;
    private static ExtraDataInfo currentExtraData;
    
    public ExtraDataInfo getExtraData() {
        return extraData;
    }
    
    public ExtraDataInfoService() {
        extraData = new ExtraDataInfo();
    }
    
    /* Initial the extra data in metric*/
    public void initExtraDataInfo(String qName) {
        if(EXTRA_DATA.equals(qName)){
            currentExtraData = new ExtraDataInfo();
        }
    }
    
    public void setInfoToExtraData(List<ExtraElementInfo> extraElement, String qName) {
        if(EXTRA_DATA.equals(qName)){
            currentExtraData.setExtra_element(extraElement);
            extraData = currentExtraData;
            currentExtraData = null;
        }
    }
}
