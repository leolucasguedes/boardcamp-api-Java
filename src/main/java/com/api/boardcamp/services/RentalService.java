package com.api.boardcamp.services;

import org.springframework.stereotype.Service;
import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.models.dto.RentalDTO;
import com.api.boardcamp.repositories.RentalRepository;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Optional<Rental> getRentalById(Long id) {
        return rentalRepository.findById(id);
    }

    public RentalDTO saveRental(RentalDTO rentalDTO) {
        Rental rental = new Rental(
                rentalDTO.getCustomerId(),
                rentalDTO.getGameId(),
                rentalDTO.getDaysRented(),
                null,
                null,
                null,
                null);
        rental = rentalRepository.save(rental);
        return convertToDTO(rental);
    }

    public void finishRental(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            if (rental.getReturnDate() == null) {
                Date returnDate = new Date();
                rental.setReturnDate(returnDate);

                Date rentDate = rental.getRentDate();
                long rentTimeMillis = rentDate.getTime();
                long returnTimeMillis = returnDate.getTime();
                long dayInMillis = 24 * 60 * 60 * 1000;
                int rentedDays = rental.getDaysRented();
                int delayDays = (int) ((returnTimeMillis - (rentTimeMillis + (dayInMillis * rentedDays)))
                        / dayInMillis);
                double pricePerDay = rentalRepository.findPricePerDayByRentalId(id);
                double delayFee = Math.max(0, delayDays) * pricePerDay;

                rental.setDelayFee(delayFee);
                rentalRepository.save(rental);
            } else {
                throw new IllegalStateException("Rental with id " + id + " has already been finished.");
            }
        } else {
            throw new IllegalArgumentException("Rental with id " + id + " not found.");
        }
    }

    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    private RentalDTO convertToDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCustomerId(),
                rental.getGameId(),
                rental.getDaysRented());
    }
}