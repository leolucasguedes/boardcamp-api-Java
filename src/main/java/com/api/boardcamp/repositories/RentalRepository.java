package com.api.boardcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.api.boardcamp.models.entities.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT g.pricePerDay FROM Rental r JOIN Game g ON r.gameId = g.id WHERE r.id = :rentalId")
    double findPricePerDayByRentalId(@Param("rentalId") Long rentalId);
}