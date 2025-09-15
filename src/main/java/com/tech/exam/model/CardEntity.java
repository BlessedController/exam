package com.tech.exam.model;

import com.tech.exam.model.enums.CardStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.tech.exam.model.enums.CardStatus.ACTIVE;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class CardEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(unique = true)
    Long costumerId;

    @Column(unique = true, length = 16)
    String cardNumber;

    @Column(unique = true)
    Integer cvv;

    @Column(length = 4)
    String pin;

    @Column
    @Builder.Default
    CardStatus status = ACTIVE;

    @CreationTimestamp
    LocalDateTime createDate;

    @Builder.Default
    LocalDateTime expiryDate = LocalDate.now().plusYears(5L).atStartOfDay();


}
