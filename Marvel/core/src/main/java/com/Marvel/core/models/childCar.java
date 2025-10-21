package com.Marvel.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL )
public class childCar {
    @ValueMapValue
    private String Image;
    
    @ValueMapValue
    private String richtext;

    @ValueMapValue
    private String Path;

    public String getImage() {
        return Image;
    }

    public String getRichtext() {
        return richtext;
    }

    public String getPath() {
        return Path;
    }
    

}
