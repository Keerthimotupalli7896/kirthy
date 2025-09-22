package com.Marvel.core.models;


import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class carouselChild {
    @ValueMapValue
    private String text;
    @ValueMapValue
    private String pathfield;
    @ChildResource
    private List<carouselChild1> multifield;

    public String getText() {
        return text;
    }
    public List<carouselChild1> getMultifield() {
        return multifield;
    }
    public String getPathfield() {
        return pathfield;
    }

}
