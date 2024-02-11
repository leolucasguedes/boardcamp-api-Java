package com.api.boardcamp.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.repositories.RentalRepository;
import com.api.boardcamp.services.RentalService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @InjectMocks
    private RentalService rentalService;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllRentals() {

        when(rentalRepository.findAll()).thenReturn(new ArrayList<>());

        List<Rental> rentals = rentalService.getAllRentals();

        verify(rentalRepository).findAll();

        assertEquals(0, rentals.size());
    }

    @Test
    public void testGetAllRentalsEmptyList() {
        when(rentalRepository.findAll()).thenReturn(Collections.emptyList());
        List<Rental> rentals = rentalService.getAllRentals();
        assertEquals(0, rentals.size());
    }
}