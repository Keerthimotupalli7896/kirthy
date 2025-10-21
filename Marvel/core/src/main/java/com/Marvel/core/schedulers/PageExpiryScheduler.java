package com.Marvel.core.schedulers;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.resource.LoginException;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.metatype.annotations.Designate;

import com.day.cq.replication.Replicator;
import com.Marvel.core.osgi.config.Configuration;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.ReplicationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = Configuration.class)
public class PageExpiryScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(PageExpiryScheduler.class);

    @Reference
    private Scheduler scheduler;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private Replicator replicator;

    private String cronExpression;

    @Activate
    protected void activate(Configuration config) {
        cronExpression = config.scheduler_expression();
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name("PageExpiryScheduler");
        scheduler.schedule(this, options);
        log.info("✅ Page Expiry Scheduler activated with CRON: {}", cronExpression);
    }

    @Override
    public void run() {
        log.info("🕒 Running Page Expiry Scheduler...");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "hemanth"))) {

            Resource contentRoot = resolver.getResource("/content/Marvel");
            if (contentRoot == null) {
                log.warn("⚠️ /content resource not found!");
                return;
            }

            log.info("✅ /content resource found.");

            Iterator<Resource> pages = contentRoot.listChildren();
            if (!pages.hasNext()) {
                log.warn("⚠️ No child pages found under /content.");
                return;
            }

            Calendar now = Calendar.getInstance();

            while (pages.hasNext()) {
                Resource page = pages.next();
                String pagePath = page.getPath();

                Resource jcrContent = page.getChild("jcr:content");
                if (jcrContent == null) {
                    log.warn("⚠️ jcr:content missing for page: {}", pagePath);
                    continue;
                }

                ValueMap properties = jcrContent.getValueMap();
                Calendar expiryDate = properties.get("Expirydate", Calendar.class);

                if (expiryDate == null) {
                    log.warn("⚠️ Expirydate missing or invalid for page: {}", pagePath);
                    continue;
                }

                log.debug("📅 Page: {}, Expirydate: {}, Now: {}", pagePath, expiryDate.getTime(), now.getTime());

                Session session = resolver.adaptTo(Session.class);
                if (session == null) {
                    log.error("❌ Could not adapt ResourceResolver to Session.");
                    return;
                }

                try {
                    // Check if the page is expired
                 if (expiryDate.before(now)) {
                    replicator.replicate(session, ReplicationActionType.DEACTIVATE, pagePath);
                    log.info("🟥 Unpublished (Expired): {}", pagePath);
                  } else if (!expiryDate.before(now)) {
                    replicator.replicate(session, ReplicationActionType.ACTIVATE, pagePath);
                    log.info("🟩 Published (Future or Current): {}", pagePath);
                  } else {
                    log.debug("⏳ No action needed for page: {} (Expiry: {})", pagePath, expiryDate.getTime());
                  }


                } catch (ReplicationException e) {
                    log.error("❌ Replication failed for {}: {}", pagePath, e.getMessage(), e);
                }
            }

        } catch (LoginException e) {
            log.error("❌ Failed to get service user session (LoginException): ", e);
        } catch (Exception e) {
            log.error("❌ Unexpected error during scheduler run: ", e);
        }
    }
}
