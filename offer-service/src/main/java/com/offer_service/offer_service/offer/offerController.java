package com.offer_service.offer_service.offer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/offers")
public class offerController {

    private final OfferService service;
    private final OfferRepository offerRepository;


    // Constructor to initialize both service and repository
    public offerController(OfferService service, OfferRepository offerRepository) {
        this.service = service;
        this.offerRepository = offerRepository;
    }


    @PostMapping
    public ResponseEntity<Integer> createOffer(@RequestBody @Valid OfferRequest request) {
        return ResponseEntity.ok(this.service.createOffer(request));
    }

    @GetMapping
    public ResponseEntity<List<OfferResponse>> findAll(){
        return ResponseEntity.ok(this.service.findAll());
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OfferResponse>> findOffersByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(this.service.findOffersByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable("id") Integer id) {
        this.service.deleteOffer(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{offer-id}")
    public ResponseEntity<OfferResponse> findById(
            @PathVariable("offer-id") Integer offerId
    ){
        return ResponseEntity.ok(service.findById(offerId));
    }


    @PutMapping("/{id}/update-seats")
    public ResponseEntity<Void> updateAvailableSeats(
            @PathVariable Integer id,
            @RequestParam Integer newAvailableSeats
    ) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));

        offer.setAvailableSeats(newAvailableSeats);
        offerRepository.save(offer);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOffer(
            @PathVariable("id") Integer id,
            @RequestBody @Valid OfferRequest request
    ) {
        this.service.updateOffer(id, request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint is working!");
    }

}
