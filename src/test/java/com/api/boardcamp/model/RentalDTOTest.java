package com.api.boardcamp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.models.dto.RentalDTO;

public class RentalDTOTest {

    @Test
    public void testConversionToDTO() {
        Rental rental = new Rental();
        rental.setId(1L);
        rental.setCustomerId(2L);
        rental.setGameId(3L);
        rental.setDaysRented(4);

        RentalDTO dto = new RentalDTO(rental.getId(), rental.getCustomer().getId(), rental.getGame().getId(),
                rental.getDaysRented());

        assertEquals(rental.getId(), dto.getId());
        assertEquals(rental.getCustomer().getId(), dto.getCustomerId());
        assertEquals(rental.getGame().getId(), dto.getGameId());
        assertEquals(rental.getDaysRented(), dto.getDaysRented());
    }
}