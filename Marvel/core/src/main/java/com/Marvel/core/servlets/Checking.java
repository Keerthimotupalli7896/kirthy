package com.Marvel.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.methods=POST",
        "sling.servlet.paths=/bin/deleteContentNode"
    }
)
public class Checking extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String path = request.getParameter("path");

        if (path == null || path.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Path parameter is missing.");
            return;
        }

  
        String modifiedPath = path.replace("_jcr_content", "jcr:content");

        ResourceResolver resolver = request.getResourceResolver();
        Resource resource = resolver.getResource(modifiedPath);

        if (resource == null) {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Resource not found: " + modifiedPath);
            return;
        }

        try {
            ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);

            if (valueMap != null) {
                if (valueMap.containsKey("cssClass")) {
                    valueMap.remove("cssClass");
                }

                resolver.commit();

                response.setStatus(SlingHttpServletResponse.SC_OK);
                response.getWriter().write("Property 'cssClass' removed successfully from: " + modifiedPath);
            } else {
                response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Unable to adapt resource to ModifiableValueMap.");
            }

        } catch (PersistenceException e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error committing changes: " + e.getMessage());
        }
    }
}
