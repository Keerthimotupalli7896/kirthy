package com.Marvel.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselDemo {
    @ChildResource(name="multifield1")
    private List<carouselChild> multifield1;

    public List<carouselChild> getMultifield1() {
        return multifield1;
    }
 

}
