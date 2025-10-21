package com.Marvel.core.osgi.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "Page Expiry Publish/Unpublish Scheduler")
    public @interface Configuration {
        @AttributeDefinition(
                name = "Cron Expression",
                description = "Scheduler runs based on this expression (e.g., */3 * * * * ?)"
        )
        String scheduler_expression() default "0 0/3 * * * ?";
    }
