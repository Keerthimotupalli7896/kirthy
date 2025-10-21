package com.Marvel.core.schedulers;
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

import com.Marvel.core.osgi.config.TestDemoData;


@Component(service = Runnable.class, enabled = true, immediate = true)
@Designate(ocd=TestDemoData.class)
public class TestDemo implements Runnable {

  private static final Logger LOG = LoggerFactory.getLogger(TestDemo.class);

  @Reference
  Scheduler scheduler;

  @Override
  public void run() {
    LOG.info("The scheduler TestDemo is getting Executed");
  }

  @Activate
  @Modified
  public void Activate(TestDemoData testDemoData) {
    LOG.info("Activating TestDemo Scheduler with expression: {}", testDemoData.SchedularExpression());
    if (testDemoData.enable()) {
      ScheduleOptions options = scheduler.EXPR(testDemoData.SchedularExpression());
      options.canRunConcurrently(false);
      options.name(testDemoData.SchedularName());
      scheduler.schedule(this, options);
      LOG.info("The scheduler is successfully scheduled with name: {}", testDemoData.SchedularName());
    }
  }

  @Deactivate
  public void deactivate(TestDemoData testDemoData) {
      LOG.info("Deactivating TestDemo Scheduler with name: {}", testDemoData.SchedularName());
      scheduler.unschedule(testDemoData.SchedularName());
  }
}
