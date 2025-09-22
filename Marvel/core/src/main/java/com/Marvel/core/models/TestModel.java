package com.Marvel.core.models;

import javax.inject.Inject;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class ,defaultInjectionStrategy =DefaultInjectionStrategy.OPTIONAL)
public class TestModel {
   @ValueMapValue
private String text;
@ValueMapValue
private String check;
@ValueMapValue
private String select;
@ValueMapValue
private String file;

    public String getText() {
        return text;
    }
    public String getCheck() {
        return check;
    }
    public String getSelect() {
        return select;
    }
    public String getFile() {
        return file;
    }

}
