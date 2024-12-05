package com.demand_service.demand_service.demand;

import com.demand_service.demand_service.Offer.OfferClient;
import com.demand_service.demand_service.Offer.OfferResponse;
import com.demand_service.demand_service.exception.BusinessException;
import com.demand_service.demand_service.kafka.DemandConfirmation;
import com.demand_service.demand_service.kafka.DemandProducer;
import com.demand_service.demand_service.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandService {

    private final OfferClient offerClient;
    private final DemandRepository demandRepository;
    @Autowired
    public DemandService(DemandRepository demandRepository, OfferClient offerClient) {
        this.demandRepository = demandRepository;
        this.offerClient = offerClient;
    }


    @Value("${application.config.offer-url}")
    private String offerUrl;

    public DemandResponse createDemand(DemandRequest request) {
        // Fetch the offer details using Feign client
        OfferResponse offer = offerClient.getOffer(request.offerId());

        // Create a new demand
        Demand demand = new Demand();
        demand.setOfferId(request.offerId());
        demand.setUserId(request.userId());
        demand.setSeatsRequested(request.seatsRequested());
        demand.setStatus(DemandStatus.PENDING);


        // Save the demand to the database
        demandRepository.save(demand);

        // Return the DemandResponse
        return new DemandResponse(
                demand.getId(),
                demand.getUserId(),
                demand.getOfferId(),
                demand.getStatus(),
                demand.getSeatsRequested(),
                demand.getCreatedAt()

        );
    }


    public List<DemandResponse> getDemandsByOffer(Integer offerId) {
        // Rechercher les demandes pour une annonce spécifique
        return demandRepository.findByOfferId(offerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<DemandResponse> getDemandsByUser(String userId) {
        // Rechercher les demandes pour un utilisateur spécifique
        return demandRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DemandResponse mapToResponse(Demand demand) {
        return new DemandResponse(
                demand.getId(),               // Integer id
                demand.getUserId(),           // String userId
                demand.getOfferId(),          // Integer offerId
                demand.getStatus(),           // DemandStatus status
                demand.getSeatsRequested(),   // Integer seatsRequested
                demand.getCreatedAt()         // LocalDateTime createdAt
        );
    }


    public void deleteDemand(Integer id) {
        // Delete demand by ID
        demandRepository.deleteById(id);
    }
    public DemandResponse approveDemand(Integer demandId, String currentUserId) {
        // Fetch the demand
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        // Fetch the related offer using the Offer microservice
        OfferResponse offer = offerClient.getOffer(demand.getOfferId());

        // Check if the current user is the owner of the offer
        if (!offer.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("Only the owner of the offer can approve demands.");
        }

        // Check seat availability
        if (offer.availableSeats() >= demand.getSeatsRequested()) {
            // Update available seats in the Offer microservice
            offerClient.updateAvailableSeats(
                    offer.getId(),
                    offer.availableSeats() - demand.getSeatsRequested()
            );
            // Approve the demand
            demand.setStatus(DemandStatus.APPROVED);
        } else {
            throw new RuntimeException("Insufficient available seats to approve this demand.");
        }

        // Save the updated demand
        demandRepository.save(demand);

        // Return the updated demand response
        return new DemandResponse(
                demand.getId(),
                demand.getUserId(),
                demand.getOfferId(),
                demand.getStatus(),
                demand.getSeatsRequested(),
                demand.getCreatedAt()
        );
    }

    public DemandResponse denyDemand(Integer demandId, String currentUserId) {
        // Fetch the demand
        Demand demand = demandRepository.findById(demandId)
                .orElseThrow(() -> new RuntimeException("Demand not found"));

        // Fetch the related offer using the Offer microservice
        OfferResponse offer = offerClient.getOffer(demand.getOfferId());

        // Check if the current user is the owner of the offer
        if (!offer.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("Only the owner of the offer can deny demands.");
        }

        // Deny the demand
        demand.setStatus(DemandStatus.DENIED);

        // Save the updated demand
        demandRepository.save(demand);

        // Return the updated demand response
        return new DemandResponse(
                demand.getId(),
                demand.getUserId(),
                demand.getOfferId(),
                demand.getStatus(),
                demand.getSeatsRequested(),
                demand.getCreatedAt()
        );
    }


}
