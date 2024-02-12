package com.api.boardcamp.models.dto;

import jakarta.validation.constraints.*;

public class GameDTO {

    @NotBlank(message = "O nome do jogo é obrigatório")
    @Size(min = 2, max = 100, message = "O nome do jogo deve ter entre 2 e 100 caracteres")
    private String name;

    @NotBlank(message = "A URL da imagem é obrigatória")
    private String image;

    @NotNull(message = "O estoque total é obrigatório")
    @Min(value = 1, message = "O estoque total deve ser maior que 0")
    private int stockTotal;

    @NotNull(message = "O preço por dia é obrigatório")
    @Positive(message = "O preço por dia deve ser maior que 0")
    private Double pricePerDay;

    public GameDTO(String name, String image, int stockTotal, Double pricePerDay) {
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(Integer stockTotal) {
        this.stockTotal = stockTotal;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }
}