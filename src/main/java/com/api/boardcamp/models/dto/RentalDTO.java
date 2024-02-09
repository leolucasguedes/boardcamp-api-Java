package com.api.boardcamp.models.dto;

import jakarta.validation.constraints.*;

public class RentalDTO {

    private Long id;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Game ID is required")
    private Long gameId;

    @NotNull(message = "Number of days rented is required")
    @Min(value = 1, message = "Number of days rented must be at least 1")
    private Integer daysRented;

    public RentalDTO() {
    }

    public RentalDTO(Long id, Long customerId, Long gameId, Integer daysRented) {
        this.id = id;
        this.customerId = customerId;
        this.gameId = gameId;
        this.daysRented = daysRented;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }
}