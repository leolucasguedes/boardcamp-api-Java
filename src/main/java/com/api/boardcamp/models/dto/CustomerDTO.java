package com.api.boardcamp.models.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class CustomerDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    private String name;

    @NotBlank(message = "O telefone é obrigatório")
    @Size(min = 10, max = 11, message = "O telefone deve ter entre 10 e 11 caracteres")
    private String phone;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    @Past(message = "A data de aniversário deve estar no passado")
    private LocalDate birthday;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String phone, String cpf, LocalDate birthday) {
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
