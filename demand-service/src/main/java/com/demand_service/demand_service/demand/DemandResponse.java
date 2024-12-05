package com.demand_service.demand_service.demand;

import lombok.Setter;

import java.time.LocalDateTime;
public record DemandResponse(
        Integer id,
        String userId,
        Integer offerId,
        DemandStatus status,
        Integer seatsRequested,
        LocalDateTime createdAt
) {

}
