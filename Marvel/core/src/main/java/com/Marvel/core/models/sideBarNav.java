package com.Marvel.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class sideBarNav {
    @ValueMapValue
    private String logoPath;
    @ValueMapValue
    private String logoMobile;
    @ValueMapValue
    private String logoLink;
    @ValueMapValue
    private String checkbox;
  
    @ChildResource
    private List<sideBartab1>multifield;
    @ChildResource
    private List<sideBartab2>multifield1;

    public List<sideBartab1> getMultifield() {
        return multifield;
    }
    public List<sideBartab2> getMultifield1() {
        return multifield1;
    }
    public String getLogoPath() {
        return logoPath;
    }
    public String getLogoMobile() {
        return logoMobile;
    }
    public String getLogoLink() {
        return logoLink;
    }
    public String getCheckbox() {
        return checkbox;
    }
}
