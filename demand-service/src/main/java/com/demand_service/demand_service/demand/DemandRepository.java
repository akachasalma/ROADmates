package com.demand_service.demand_service.demand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {
    List<Demand> findByStatus(DemandStatus status);
    List<Demand> findByOfferId(Integer offerId); // Méthode pour les annonces
    List<Demand> findByUserId(String userId);       // Méthode pour les utilisateurs
    List<Demand> findByUserIdAndStatus(String userId, DemandStatus status);
    List<Demand> findByCreatedAtAfter(LocalDateTime date);
    void deleteByUserId(String userId);

    void deleteByStatus(DemandStatus status);

}
