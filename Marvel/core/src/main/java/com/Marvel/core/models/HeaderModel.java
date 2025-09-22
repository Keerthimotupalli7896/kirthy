package com.Marvel.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
 @ValueMapValue
 private String textfield;
 @ValueMapValue
 private String  checkbox;
 @ValueMapValue
 private String pathfield;

 @ChildResource
  private List<headerChild>  multifield;
  
 public String getTextfield() {
    return textfield;
 }
 public String getCheckbox() {
    return checkbox;
 }
 public List<headerChild> getMultifield() {                                                  
    return multifield;
}
 public String getPathfield() {
    return pathfield;
 }
 
}
