package com.tech.exam.model;

import com.tech.exam.model.enums.CustomerStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(unique = true, nullable = false, length = 7)
    String finCode;

    @Column(nullable = false, length = 50)
    String name;

    @Column(nullable = false, length = 50)
    String surname;

    @Column
    String cardNumber;

    @Column(nullable = false)
    @Enumerated(STRING)
    CustomerStatus status;

}
