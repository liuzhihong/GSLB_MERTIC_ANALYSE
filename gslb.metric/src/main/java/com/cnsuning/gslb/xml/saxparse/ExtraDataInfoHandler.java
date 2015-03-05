package com.cnsuning.gslb.xml.saxparse;

import java.util.List;

import com.cnsuning.gslb.node.model.ExtraDataEntity;
import com.cnsuning.gslb.node.model.ExtraElementEntity;

public class ExtraDataInfoHandler {
    
    /* EXTRA_DATA related info.*/
    private static ExtraDataEntity extraData;
    private static ExtraDataEntity currentExtraData;
    
    public ExtraDataEntity getExtraData() {
        return extraData;
    }
    
    public ExtraDataInfoHandler() {
        extraData = new ExtraDataEntity();
    }
    
    /**
     * Initial the extra data under the metric label
     */
    public void initExtraDataInfo() {
        currentExtraData = new ExtraDataEntity();
    }
    
    /**
     * Set the extra element under the extra data label
     * to the extra bean.
     * @param extraElement
     */
    public void setInfoToExtraData(List<ExtraElementEntity> extraElement) {
        currentExtraData.setExtra_element(extraElement);
        extraData = currentExtraData;
        currentExtraData = null;
    }
    
    public void clearExtraData(){
        
    }
}
