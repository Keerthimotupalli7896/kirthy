package com.Marvel.core.schedulers;

import java.io.IOException;
import java.nio.CharBuffer;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Marvel.core.osgi.config.configurationosgi;


@Component(service = scheddule.class,immediate = true)
@Designate(ocd = configurationosgi .class)

public class scheddule implements Runnable {
 private static final Logger log=LoggerFactory.getLogger(scheddule.class);
    @Reference
    private Scheduler scheduler;

    public void Schedule(configurationosgi config){
        if(config.isEnabled()){
            ScheduleOptions so=scheduler.EXPR(config.getCornExpression());
            so.name(config.getSchedularName());
            so.canRunConcurrently(config.isCanRunConcurrent());
            scheduler.schedule(this,so);
        }
    }
    @Activate
    @Modified
    public void activate(configurationosgi config){
        log.info("the activate modethod ");
        this.Schedule(config);
    }
    @Deactivate
    public void deactivate(configurationosgi config){
        log.info("Schedular is done");
        scheduler.unschedule(config.getSchedularName());
    }
    @Override
    public void run() {
        log.info("Schedular is executed");
        }
}
