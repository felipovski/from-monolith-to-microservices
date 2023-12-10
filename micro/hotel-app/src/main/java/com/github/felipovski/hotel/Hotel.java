package com.github.felipovski.hotel;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Hotel extends PanacheEntity {

    public Long travelOrderId;
    public Integer nights;

    public static Hotel findByTravelOrderId(long travelOrderId) {
        return find("travelOrderId", travelOrderId).firstResult();
    }
}
