package com.api.boardcamp.services;

import org.springframework.stereotype.Service;
import com.api.boardcamp.models.entities.Game;
import com.api.boardcamp.models.entities.Rental;
import com.api.boardcamp.models.entities.Customer;
import com.api.boardcamp.models.dto.RentalDTO;
import com.api.boardcamp.repositories.RentalRepository;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.exceptions.GameUnavailableException;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final GameService gameService;
    private final CustomerService customerService;

    public RentalService(RentalRepository rentalRepository, GameService gameService, CustomerService customerService) {
        this.rentalRepository = rentalRepository;
        this.gameService = gameService;
        this.customerService = customerService;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rental not found"));
    }

    public RentalDTO saveRental(RentalDTO rentalDTO) {

        Game game = gameService.getGameById(rentalDTO.getGameId());
        if (game == null) {
            throw new NotFoundException("Game not found");
        }

        Customer customer = customerService.getCustomerById(rentalDTO.getCustomerId());
        if (customer == null) {
            throw new NotFoundException("Customer not found");
        }

        isGameAvailable(game);

        LocalDate rentDate = LocalDate.now();
        double originalPrice = calculateOriginalPrice(game.getPricePerDay(), rentalDTO.getDaysRented());

        Rental rental = new Rental();
        rental.setRentDate(rentDate);
        rental.setReturnDate(null);
        rental.setDaysRented(rentalDTO.getDaysRented());
        rental.setOriginalPrice(originalPrice);
        rental.setDelayFee(0.0);
        rental.setCustomer(customer);
        rental.setGame(game);

        rental = rentalRepository.save(rental);
        return convertToDTO(rental);
    }

    public void finishRental(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental rental = optionalRental.get();
            if (rental.getReturnDate() == null) {
                LocalDate returnDate = LocalDate.now();
                rental.setReturnDate(returnDate);

                LocalDate rentDate = rental.getRentDate();
                int daysRented = rental.getDaysRented();
                double pricePerDay = rental.getGame().getPricePerDay();
                int delayDays = (int) returnDate.datesUntil(rentDate.plusDays(daysRented)).count();
                double delayFee = Math.max(0, delayDays * pricePerDay);

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

    private void isGameAvailable(Game game) {
        int stockTotal = game.getStockTotal();
        long rentedCount = rentalRepository.countByGameIdAndReturnDateIsNull(game.getId());

        if (rentedCount >= stockTotal) {
            throw new GameUnavailableException("No availability for the selected game.");
        }
    }

    private Double calculateOriginalPrice(Double pricePerDay, int daysRented) {
        return pricePerDay * daysRented;
    }

    private RentalDTO convertToDTO(Rental rental) {
        return new RentalDTO(
                rental.getId(),
                rental.getCustomer().getId(),
                rental.getGame().getId(),
                rental.getDaysRented());
    }
}