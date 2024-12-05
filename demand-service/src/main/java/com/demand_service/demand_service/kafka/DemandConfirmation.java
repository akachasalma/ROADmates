package com.demand_service.demand_service.kafka;

import java.time.LocalDateTime;

public record DemandConfirmation(
        Integer demandId,
        Integer userId,
        Integer offerId,
        String status,
        LocalDateTime createdAt

) {
}
