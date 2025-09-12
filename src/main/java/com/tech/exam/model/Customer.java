package com.tech.exam.model;

import com.tech.exam.model.enums.CustomerStatus;
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

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "fin_code", unique = true, nullable = false, length = 7)
    String finCode;

    @Column(name = "name", nullable = false, length = 50)
    String name;

    @Column(name = "surname", nullable = false, length = 50)
    String surname;

    @Column(name = "card_number", unique = true, length = 16)
    String cardNumber;

    @Column(name = "status", nullable = false)
    CustomerStatus status;

}
