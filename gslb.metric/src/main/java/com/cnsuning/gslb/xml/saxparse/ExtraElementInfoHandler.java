package com.cnsuning.gslb.xml.saxparse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import com.cnsuning.gslb.node.data.model.ExtraElementEntity;


public class ExtraElementInfoHandler {
    /* EXTRA_ELEMENT related info.*/
    private static List<ExtraElementEntity> extraElement;
    private static ExtraElementEntity currentExtraElement;
    private static final String EXTRA_ELEMENT_NAME = "NAME";
    private static final String EXTRA_ELEMENT_VAL = "VAL";
    
    public List<ExtraElementEntity> getExtraElement() {
        return extraElement;
    }
    
    public ExtraElementInfoHandler() {
        extraElement = new ArrayList<ExtraElementEntity>();
    }
    
    /**
     * Initial the extra element under the extra data label
     * @param attributes
     */
    public void initExtraElementInfo(Attributes attributes) {
        currentExtraElement = new ExtraElementEntity();
        currentExtraElement.setName(attributes.getValue(EXTRA_ELEMENT_NAME));
        currentExtraElement.setVal(attributes.getValue(EXTRA_ELEMENT_VAL));
    }
    
    /**
     * Set the extra elements to a list
     */
    public void setInfoToExtraElement() {
        extraElement.add(currentExtraElement);
        currentExtraElement = null;
    }
    
    public void initExtraElementList(){
        extraElement = new ArrayList<ExtraElementEntity>();
    }
}
