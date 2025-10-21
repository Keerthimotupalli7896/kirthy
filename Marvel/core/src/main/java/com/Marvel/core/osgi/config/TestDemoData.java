package com.Marvel.core.osgi.config;


import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="TestDemoData", description = "Test Demo Data Class")
public @interface TestDemoData {

  @AttributeDefinition(name="Schedular Name",description = "Name for the Schedular",defaultValue = "TestDemo")
  public String SchedularName();

  @AttributeDefinition(name="Schedular Expression",description = "Cron Expression")
  public String SchedularExpression();

  @AttributeDefinition(name="Enable feature")
  public boolean enable() default false;

}
