package com.offer_service.offer_service.offer;

import org.springframework.stereotype.Service;

@Service
public class OfferMapper {

    /**
     * Converts an OfferRequest into an Offer entity.
     *
     * @param request the offer request
     * @return the Offer entity
     */
    public Offer toOffer(OfferRequest request) {
        return Offer.builder()
                .id(request.id())
                .userId(request.userId())
                .dep_location(request.dep_location())
                .arr_location(request.arr_location())
                .time(request.time())
                .price(request.price())
                .availableSeats(request.availableSeats())
                .matriculationNumber(request.matriculationNumber())
                .color(request.color())
                .brand(request.brand())
                .build();
    }

    /**
     * Converts an Offer entity into an OfferResponse.
     *
     * @param offer the Offer entity
     * @return the OfferResponse
     */
    public OfferResponse toOfferResponse(Offer offer) {
        return new OfferResponse(
                offer.getId(),
                offer.getUserId(),
                offer.getDep_location(),
                offer.getArr_location(),
                offer.getTime(),
                offer.getPrice(),
                offer.getAvailableSeats(),
                offer.getMatriculationNumber(),
                offer.getBrand(),
                offer.getColor()

        );
    }

    /**
     * Updates an existing Offer entity with data from an OfferRequest.
     *
     * @param offer   the existing Offer entity
     * @param request the offer request with updated data
     */
    public void updateOfferFromRequest(Offer offer, OfferRequest request) {
        offer.setDep_location(request.dep_location());
        offer.setArr_location(request.arr_location());
        offer.setTime(request.time());
        offer.setPrice(request.price());

    }


}
