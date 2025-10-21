package com.Marvel.core.listeners;

import java.util.HashMap;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = DemoChid.class)
public class DemoChid {
    @Reference
    ResourceResolverFactory factory;
  
    public ResourceResolver getResourceResolver() {

        ResourceResolver resolver =null;
        try{
        HashMap<String,Object> obj= new HashMap<>();
        obj.put(ResourceResolverFactory.SUBSERVICE,"demo");
        resolver= factory.getServiceResourceResolver(obj);
       
        }
        catch(LoginException e){
            e.printStackTrace();
        }
         return resolver;
    }
}
