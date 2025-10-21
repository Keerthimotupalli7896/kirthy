package com.Marvel.core.osgi.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="custom schedular configuration",description = "Osgi configuration of custom schedular")
public @interface configurationosgi {

    @AttributeDefinition(name="schedular name",
    description = "enter the name of the schedular",
    type= AttributeType.STRING)public String getSchedularName() default "sepschedular";

    @AttributeDefinition(name="corn expression",
    description = "schedule using corn expression",
    type= AttributeType.STRING)public String getCornExpression() default "*/5 * * * *";

    @AttributeDefinition(name="Can Run concurrent",
    description = "select to run",
    type= AttributeType.BOOLEAN)public boolean isCanRunConcurrent() default true;

    @AttributeDefinition(name="enable",
    description = "schedule is enabled",
    type= AttributeType.BOOLEAN)public boolean isEnabled() default true;
}
