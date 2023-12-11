package com.github.felipovski.travelorder;

import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@Path("travelorder")
public class TravelOrderResource {

    @RestClient
    FlightService flightService;
    @RestClient
    HotelService hotelService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll().stream()
                .map(order -> TravelOrderDTO.of(
                        order,
                        flightService.findByTravelOrderId(order.id),
                        hotelService.findByTravelOrderId(order.id))
                ).toList();
    }

    @GET
    @Path("findById")
    public TravelOrder findById(@QueryParam("id") long id) {
        return TravelOrder.findById(id);
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TravelOrder newTravelOrder(TravelOrderDTO orderDTO) {
        var order = new TravelOrder();
        order.id = null;
        order.persist();

        var flight = new FlightDTO();
        flight.setFromAirport(orderDTO.getFromAirport());
        flight.setToAirport(orderDTO.getToAirport());
        flight.setTravelOrderId(order.id);
        flightService.newFlight(flight);

        var hotel = new HotelDTO();
        hotel.setNights(orderDTO.getNights());
        hotel.setTravelOrderId(order.id);
        hotelService.newHotel(hotel);

        return order;
    }
}
