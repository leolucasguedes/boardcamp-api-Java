package com.api.boardcamp.models.entities;

import javax.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "days_rented")
    private Integer daysRented;

    @Column(name = "rent_date")
    private Date rentDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "original_price")
    private Double originalPrice;

    @Column(name = "delay_fee")
    private Double delayFee;

    public Rental() {
    }

    public Rental(Long customerId, Long gameId, Integer daysRented, Date rentDate, Date returnDate,
            Double originalPrice, Double delayFee) {
        this.customerId = customerId;
        this.gameId = gameId;
        this.daysRented = daysRented;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.originalPrice = originalPrice;
        this.delayFee = delayFee;
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

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getDelayFee() {
        return delayFee;
    }

    public void setDelayFee(Double delayFee) {
        this.delayFee = delayFee;
    }
}