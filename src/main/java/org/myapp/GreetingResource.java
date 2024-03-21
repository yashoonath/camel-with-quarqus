package org.myapp;

import org.apache.camel.FluentProducerTemplate;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    FluentProducerTemplate fluentProducerTemplate;

    @GET
    @Path("{name}")
    @RolesAllowed({"admin","tester"})
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
        return fluentProducerTemplate
                .withBody(name)
                .to("direct:greetings")
                .request(String.class);
    }
}