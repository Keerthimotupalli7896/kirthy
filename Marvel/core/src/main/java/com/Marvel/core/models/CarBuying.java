package com.Marvel.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarBuying {

    @ValueMapValue
    private String richtext;
    
    @ValueMapValue
    private  String textarea;
    
    @ValueMapValue
    private String image;

    public String getRichtext() {
        return richtext;
    }

    public String getTextarea() {
        return textarea;
    }

    public String getImage() {
        return image;
    }

}
