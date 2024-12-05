package com.Feedback_service.Feedback_service.Feddback;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "user_feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Column(nullable = false)
   // private String rideReference; // Reference for the ride related to the feedback

    @Column(nullable = false)
    private Integer rating; // Rating given by the user

    @Column(columnDefinition = "TEXT")
    private String comment; // Feedback comment

 // ID of the user who provided the feedback (linked to User microservice)
    @Column(nullable = false)
    private String giverUserId; // ID of the user making the feedback

    @Column(nullable = false)
    private String receiverUserId;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate; // Date when the feedback was created

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate; // Date when the feedback was last modified
}
