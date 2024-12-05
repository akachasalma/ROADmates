package com.demand_service.demand_service.demand;

import com.demand_service.demand_service.Offer.OfferClient;
import com.demand_service.demand_service.Offer.OfferResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/demand")
@RequiredArgsConstructor
public class demandController {

    private final DemandService service;
    private final DemandRepository repository;
    private final OfferClient offerClient;

    @PostMapping
    public ResponseEntity<DemandResponse> createDemand(@RequestBody @Valid DemandRequest request) {
        // Check availability of seats by calling Offer Service
        OfferResponse offer = offerClient.getOffer(request.offerId());

        if (offer.availableSeats() < request.seatsRequested()) {
            throw new RuntimeException("Not enough available seats");
        }

        DemandResponse response = service.createDemand(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DemandResponse>> getAllDemands() {
        // Fetch all demands from the repository
        List<Demand> demands = repository.findAll();

        // Map demands to response objects
        List<DemandResponse> demandResponses = demands.stream()
                .map(this::mapToDemandResponse)
                .collect(Collectors.toList());

        // Wrap the result in ResponseEntity
        return ResponseEntity.ok(demandResponses);
    }




    // Get demands by user ID
    @GetMapping("/by-user/{userId}")
    public ResponseEntity<List<DemandResponse>> getDemandsByUser(@PathVariable String userId) {
        // Ensure that the user ID is valid and that you handle any exceptions that might occur during DB access
        try {
            List<Demand> demands = repository.findByUserId(userId);
            List<DemandResponse> demandResponses = demands.stream()
                    .map(this::mapToDemandResponse)
                    .collect(Collectors.toList());

            // Wrap the result in ResponseEntity
            return ResponseEntity.ok(demandResponses);
        } catch (Exception e) {
            // Log the exception and rethrow or handle it gracefully
            throw new RuntimeException("Error retrieving demands for user: " + userId, e);
        }
    }


    @GetMapping("/offers/{offerId}/demands")
    public List<DemandResponse> getDemandsForOffer(@PathVariable Integer offerId, @RequestHeader("user-id") String currentUserId) {
        // Fetch the offer details
        OfferResponse offer = offerClient.getOffer(offerId);

        // Check if the current user is the owner of the offer
        if (!offer.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("Only the owner of the offer can view its demands.");
        }

        // Fetch demands related to the offer
        List<Demand> demands = repository.findByOfferId(offerId);

        // Map demands to responses
        return demands.stream()
                .map(this::mapToDemandResponse)
                .toList();
    }


    @GetMapping("/demands/{demandId}")
    public DemandResponse getDemandStatus(@PathVariable Integer demandId, @RequestHeader("user-id") String currentUserId) {
        // Fetch the demand
        Demand demand = repository.findById(demandId)
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        // Check if the current user is the owner of the demand
        if (!demand.getUserId().equals(currentUserId)) {
            throw new RuntimeException("You can only view the status of your own demands.");
        }

        // Map to response
        return mapToDemandResponse(demand);
    }


    private DemandResponse mapToDemandResponse(Demand demand) {
        return new DemandResponse(
                demand.getId(),              // Integer id
                demand.getUserId(),          // String userId
                demand.getOfferId(),         // Integer offerId
                demand.getStatus(),
                demand.getSeatsRequested(),// DemandStatus status
                demand.getCreatedAt()        // LocalDateTime createdAt
        );
    }
    @PutMapping("/{demandId}/approve")
    public ResponseEntity<DemandResponse> approveDemand(
            @PathVariable Integer demandId,
            @RequestHeader("user-id") String ownerId) {
        // Appeler la méthode approveDemand dans le service
        DemandResponse response = service.approveDemand(demandId, ownerId);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{demandId}/deny")
    public ResponseEntity<DemandResponse> denyDemand(
            @PathVariable Integer demandId,
            @RequestHeader("user-id") String ownerId) {
        // Call the denyDemand method in the service layer
        DemandResponse response = service.denyDemand(demandId, ownerId);
        return ResponseEntity.ok(response);
    }

}
