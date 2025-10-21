package com.Marvel.core.servlets;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,immediate = true,property = "sling.servlet.paths=/bin/demo/kee")
@SlingServletResourceTypes(resourceTypes = "Marvel/components/Carousel",
selectors = {"add","sub"},
extensions = {"json","txt","html"})
public class Demo extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        JsonObjectBuilder job=Json.createObjectBuilder();
        job.add("surge", "jheuh");
    }  
      
}
