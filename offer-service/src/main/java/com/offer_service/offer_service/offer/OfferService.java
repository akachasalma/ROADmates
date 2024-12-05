package com.offer_service.offer_service.offer;

import com.offer_service.offer_service.User.UserClient2;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OfferService {

    @Autowired
    private final OfferRepository repository;
    private final OfferMapper mapper;
    private final UserClient2 userclient;
    public Integer createOffer(OfferRequest request) {
        var user = userclient.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + request.userId()));
        var offer = mapper.toOffer(request);
        offer.setUserId(user.id());
        return repository.save(offer).getId();
    }

    public OfferResponse findById(Integer offerId) {
        return repository.findById(offerId)
                .map(mapper::toOfferResponse)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with the ID:: " + offerId));

    }
    public List<OfferResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toOfferResponse)
                .collect(Collectors.toList());
    }


    public OfferResponse updateOffer(Integer offerId, OfferRequest request) {
        var offer = repository.findById(offerId)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with the ID: " + offerId));

        mapper.updateOfferFromRequest(offer, request); // Update fields using the mapper
        var updatedOffer = repository.save(offer);

        return mapper.toOfferResponse(updatedOffer);
    }
    public void deleteOffer(Integer offerId) {
        if (!repository.existsById(offerId)) {
            throw new EntityNotFoundException("Offer not found with the ID: " + offerId);
        }
        repository.deleteById(offerId);
    }

    public List<OfferResponse> findOffersByPriceRange(Double minPrice, Double maxPrice) {
        return repository.findAllByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(mapper::toOfferResponse)
                .collect(Collectors.toList());
    }

    public List<OfferResponse> findOffersByUserId(String userId) {
        // Validate if the user exists
        var user = userclient.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Find all offers by userId
        return repository.findAllByUserId(user.id())
                .stream()
                .map(mapper::toOfferResponse)
                .collect(Collectors.toList());
    }





}

