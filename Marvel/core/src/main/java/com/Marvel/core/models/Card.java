package com.Marvel.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.resource.Resource;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Card {

    @ValueMapValue
    private String Richtext;

    @ValueMapValue
    private String textarea;

    public String getRichtext() {
        return Richtext;
    }

    public String getTextarea() {
        return textarea;
    }

    public String getImage() {
        return image;
    }

    public String getPath() {
        return path;
    }

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String path;

}
