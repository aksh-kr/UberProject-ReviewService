package com.example.UberReviewService.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "bookingreview")
public class Review {

    @Id // this annotation makes the id property a primary key of the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String content;

    Double rating;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // tells spring about the formats of Date object to be stored i.e. Date / Time / TimeStamp
    @CreatedDate // this annotation tells spring that only handle for object creation
    Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate // this annotation tells spring that only handle for object update
    Date updatedAt;
}
