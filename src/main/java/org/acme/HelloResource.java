package org.acme;

import java.time.LocalDateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/hello")
public class HelloResource {

    @GET
    @Counted(name = "helloChecks", description = "How many hellos.")
    @Timed(name = "helloTimer", description = "Time measurements about the /hello endpoint.", unit = MetricUnits.MILLISECONDS)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello" + " at " + LocalDateTime.now() ;
    }
}