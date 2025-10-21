package com.Marvel.core.models;


import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Car {
    @ValueMapValue
    private String richtext;

    public String getRichtext() {
        return richtext;
    }

    @ChildResource
    private List<childHighlighter> multifield;

    public List<childHighlighter> getMultifield() {
        return multifield;
    }
    


}
