package com.api.boardcamp.services;

import org.springframework.stereotype.Service;
import java.util.List;
import com.api.boardcamp.exceptions.CustomerAlreadyExistsException;
import com.api.boardcamp.exceptions.CustomerNotFoundException;
import com.api.boardcamp.models.dto.CustomerDTO;
import com.api.boardcamp.models.entities.Customer;
import com.api.boardcamp.repositories.CustomerRepository;

@Service
public class CustomerService {

    final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    public List<Customer> getCustomersByCpf(String cpf) {
        return customerRepository.findByCpfStartingWith(cpf);
    }

    public void addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getName(),
                customerDTO.getPhone(),
                customerDTO.getCpf(),
                customerDTO.getBirthday());
        if (customerRepository.findByCpf(customer.getCpf()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with the same CPF already exists");
        }
        customerRepository.save(customer);
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        existingCustomer.setName(customerDTO.getName());
        existingCustomer.setPhone(customerDTO.getPhone());
        existingCustomer.setCpf(customerDTO.getCpf());
        existingCustomer.setBirthDate(customerDTO.getBirthday());

        if (!existingCustomer.getCpf().equals(customerDTO.getCpf()) &&
                customerRepository.findByCpf(customerDTO.getCpf()).isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with the same CPF already exists");
        }

        customerRepository.save(existingCustomer);
    }
}