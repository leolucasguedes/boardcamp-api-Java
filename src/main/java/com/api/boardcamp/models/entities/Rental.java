package com.api.boardcamp.models.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rentDate")
    private LocalDate rentDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "daysRented")
    private int daysRented;

    @Column(name = "originalprice")
    private Double originalPrice;

    @Column(name = "delayFee")
    private Double delayFee;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;
}