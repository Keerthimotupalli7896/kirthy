package com.Marvel.core.listeners;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;


@Component(service =EventHandler.class, immediate = true,
property = {
    EventConstants.EVENT_TOPIC+"="+ReplicationAction.EVENT_TOPIC,
    EventConstants.EVENT_FILTER+"=(&(type=ACTIVATE)(paths=content/Marvel/us/en/*))"
      } )
public class DemoHandler implements EventHandler {
     
    public static final Logger log= LoggerFactory.getLogger(DemoHandler.class);
    @Reference
    DemoChid child;
    @Override
    public void handleEvent(Event event) {
        log.info("handle method is executde");
        String property =(String)event.getProperty("paths");
            ResourceResolver resolver=child.getResourceResolver();
            Resource resource=resolver.getResource(property+"/jcr:content");
            ModifiableValueMap valueMap= resource.adaptTo(ModifiableValueMap.class);
            valueMap.put("activated", true);
            try {
                resolver.commit();
            } catch (PersistenceException e) {
               
                e.printStackTrace();
            }
            resolver.close();
    }
}
