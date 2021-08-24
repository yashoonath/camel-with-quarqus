package org.myapp;

import org.apache.camel.FluentProducerTemplate;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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