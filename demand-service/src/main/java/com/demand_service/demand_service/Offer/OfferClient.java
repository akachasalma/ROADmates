package com.demand_service.demand_service.Offer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(
        name = "offer-service",
        url = "${application.config.offer-url}"
)
public interface OfferClient {

    @GetMapping("/{offerId}")
    OfferResponse getOffer(@PathVariable Integer offerId);
    @PutMapping("/{id}/update-seats")
    void updateAvailableSeats(@PathVariable Integer id, @RequestParam Integer newAvailableSeats);

}
