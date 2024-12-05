package com.offer_service.offer_service.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findAllByIdInOrderById( List<Integer> offerIds );
    List<Offer> findAllByPriceBetween(Double minPrice, Double maxPrice);
    List<Offer> findAllByUserId(String userId);
}

