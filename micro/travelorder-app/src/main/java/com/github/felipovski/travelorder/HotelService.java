package com.github.felipovski.travelorder;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082/hotel")
public interface HotelService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findById")
    HotelDTO findById(@QueryParam("id") long id);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findByTravelOrderId")
    HotelDTO findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    HotelDTO newHotel(HotelDTO hotel);
}
