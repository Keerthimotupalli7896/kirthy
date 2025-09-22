package com.Marvel.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import jdk.internal.loader.Resource;

@Model(adaptables = Resource.class,defaultInjectionStrategy =DefaultInjectionStrategy.OPTIONAL)
public class sideBartab3 {
    @ValueMapValue
    private String navigation;

    public String getNavigation() {
        return navigation;
    }
}
