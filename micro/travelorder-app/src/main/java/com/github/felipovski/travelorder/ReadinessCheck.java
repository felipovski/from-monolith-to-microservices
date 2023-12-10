package com.github.felipovski.travelorder;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Readiness
public class ReadinessCheck implements HealthCheck {

    @RestClient
    FlightService flightService;

    @RestClient
    HotelService hotelService;

    @Override
    public HealthCheckResponse call() {
        if (flightService.findById(1) != null && hotelService.findById(1) != null) {
            return HealthCheckResponse.up("I am ready!");
        } else {
            return HealthCheckResponse.down("I am not ready...");
        }
    }
}
