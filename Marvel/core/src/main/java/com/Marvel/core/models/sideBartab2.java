package com.Marvel.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class sideBartab2 {
    @ValueMapValue
    private String desktop;
    @ValueMapValue
    private String mobile;
    @ChildResource
    private List<sideBartab3> multifield2;
    public String getDesktop() {
        return desktop;
    }
    public String getMobile() {
        return mobile;
    }
    public List<sideBartab3> getMultifield2() {
        return multifield2;
    }

}
