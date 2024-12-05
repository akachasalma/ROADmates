package com.demand_service.demand_service.demand;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record DemandRequest(
        Integer id,
        String userId,
        Integer offerId,
        Integer seatsRequested

) {
}
