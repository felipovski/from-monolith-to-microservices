package com.github.felipovski.travelorder;

import com.github.felipovski.flight.Flight;
import com.github.felipovski.flight.FlightResource;
import com.github.felipovski.hotel.Hotel;
import com.github.felipovski.hotel.HotelResource;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("travelorder")
public class TravelOrderResource {

    @Inject
    FlightResource flightResource;
    @Inject
    HotelResource hotelResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TravelOrderDTO> orders() {
        return TravelOrder.<TravelOrder>listAll().stream()
                .map(order -> TravelOrderDTO.of(
                        order,
                        flightResource.findByTravelOrderId(order.id),
                        hotelResource.findByTravelOrderId(order.id))
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

        var flight = new Flight();
        flight.fromAirport = orderDTO.getFromAirport();
        flight.toAirport = orderDTO.getToAirport();
        flight.travelOrderId = order.id;
        flightResource.newFlight(flight);

        var hotel = new Hotel();
        hotel.nights = orderDTO.getNights();
        hotel.travelOrderId = order.id;
        hotelResource.newHotel(hotel);

        return order;
    }

}
