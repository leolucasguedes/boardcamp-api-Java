package com.api.boardcamp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.models.dto.RentalDTO;
import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.services.RentalService;
import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        try {
            Rental rental = rentalService.getRentalById(id);
            return ResponseEntity.ok(rental);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO) {
        RentalDTO savedRental = rentalService.saveRental(rentalDTO);
        return new ResponseEntity<>(savedRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Void> finishRental(@PathVariable Long id) {
        rentalService.finishRental(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}