package com.github.felipovski.hotel;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("hotel")
public class HotelResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hotel> hotels() {
        return Hotel.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findById")
    public Hotel findById(@QueryParam("id") long id) {
        return Hotel.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("findByTravelOrderId")
    public Hotel findByTravelOrderId(@QueryParam("travelOrderId") long travelOrderId) {
        return Hotel.findByTravelOrderId(travelOrderId);
    }

    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Hotel newHotel(Hotel hotel) {
        hotel.id = null;
        hotel.persist();

        return hotel;
    }
}
