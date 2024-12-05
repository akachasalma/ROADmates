package com.demand_service.demand_service.demand;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter

@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user_demand")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "offer_id", nullable = false)
    private Integer offerId;
    @Enumerated(EnumType.STRING)
    @Builder.Default // Ajout pour Builder
    private DemandStatus status = DemandStatus.PENDING;
    private Integer seatsRequested;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
