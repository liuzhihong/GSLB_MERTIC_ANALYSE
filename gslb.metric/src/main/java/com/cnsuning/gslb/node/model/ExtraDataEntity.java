package com.cnsuning.gslb.node.model;

import java.util.ArrayList;
import java.util.List;

public class ExtraDataEntity {
    private List<ExtraElementEntity> extra_element = new ArrayList<ExtraElementEntity>();

    public List<ExtraElementEntity> getExtra_element() {
        return extra_element;
    }

    public void setExtra_element(List<ExtraElementEntity> extra_element) {
        this.extra_element = extra_element;
    }
    
    
}
