package com.api.boardcamp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.api.boardcamp.models.dto.CustomerDTO;
import com.api.boardcamp.models.entities.Customer;
import com.api.boardcamp.services.CustomerService;
import com.api.boardcamp.exceptions.NotFoundException;
import com.api.boardcamp.exceptions.CustomerAlreadyExistsException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String cpf) {
        try {
            if (cpf == null) {
                return ResponseEntity.ok(customerService.getAllCustomers());
            } else {
                return ResponseEntity.ok(customerService.getCustomersByCpf(cpf));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Void> postCustomers(@Valid @RequestBody CustomerDTO customerDTO) {
        try {
            customerService.addCustomer(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (CustomerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> putCustomers(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (CustomerAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}