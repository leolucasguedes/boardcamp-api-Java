package com.api.boardcamp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.boardcamp.models.entities.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCpfStartingWith(String cpf);

    Optional<Customer> findByCpf(String cpf);
}