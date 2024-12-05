package com.offer_service.offer_service.offer;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "offer")
public class Offer {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private String userId;
    private String dep_location;
    private String arr_location;
    private String time;
    private BigDecimal price;
    private Integer availableSeats;
    private String matriculationNumber;
    private String brand;
    private String color;

    public String getOwnerId() {
        return this.userId;
    }


}
