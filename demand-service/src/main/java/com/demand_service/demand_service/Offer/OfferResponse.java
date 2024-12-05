package com.demand_service.demand_service.Offer;


public record OfferResponse(Integer id, Integer availableSeats, String userId) {
    public boolean isAvailable() {
        return availableSeats>0;
    }

    public Integer getId() {
        return id;
    }


    public String getOwnerId() {
        return userId;
    }
}
