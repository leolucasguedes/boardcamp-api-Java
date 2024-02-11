package com.api.boardcamp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;
import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.repositories.RentalRepository;
import java.util.List;

@DataJpaTest
public class RentalRepositoryIntegrationTest {

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void testFindAll() {

        Rental rental = new Rental();
        rental.setCustomerId(1L);
        rental.setGameId(2L);
        rental.setDaysRented(3);
        rentalRepository.save(rental);

        List<Rental> rentals = rentalRepository.findAll();

        assertEquals(1, rentals.size());
    }

}