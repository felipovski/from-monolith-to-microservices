package com.github.felipovski.travelorder;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.time.temporal.ChronoUnit;

@RegisterRestClient(baseUri = "http://flight-app-felipovski-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/flight")
public interface FlightService {

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    FlightDTO findById(@QueryParam("id") long id);

    @GET
    @Path("findByTravelOrderId")
    @Produces(MediaType.APPLICATION_JSON)
    @Timeout(unit = ChronoUnit.SECONDS, value = 2)
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            requestVolumeThreshold = 4,
            failureRatio = 0.5,
            delay = 5000,
            successThreshold = 2
    )
    FlightDTO findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    FlightDTO newFlight(FlightDTO flight);

    default FlightDTO fallback(long travelOrderId){
        var flight = new FlightDTO();
        flight.setFromAirport("");
        flight.setToAirport("travelOrderId");
        flight.setTravelOrderId(travelOrderId);
        return flight;
    }
}
