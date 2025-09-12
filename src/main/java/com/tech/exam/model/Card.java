package com.tech.exam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "costumer_id", unique = true)
    Long costumerId;

    @Column(name = "card_number", unique = true, length = 16)
    String cardNumber;

    @Column(name = "cvv", unique = true)
    Integer cvv;

    @Column(name = "pin")
    Integer pin;

    @CreationTimestamp
    LocalDate createDate;

    @Builder.Default
    LocalDate expiryDate = LocalDate.now().plusYears(5L);


}
