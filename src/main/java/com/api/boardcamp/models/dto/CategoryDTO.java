package com.api.boardcamp.models.dto;

import jakarta.validation.constraints.*;

public class CategoryDTO {

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(min = 2, max = 50, message = "O nome da categoria deve ter entre 2 e 50 caracteres")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}