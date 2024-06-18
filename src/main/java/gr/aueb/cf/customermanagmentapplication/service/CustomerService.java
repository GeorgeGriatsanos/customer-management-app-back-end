package gr.aueb.cf.customermanagmentapplication.service;

import gr.aueb.cf.customermanagmentapplication.dto.CustomerDTO;
import gr.aueb.cf.customermanagmentapplication.exception.CustomerNotFoundException;
import gr.aueb.cf.customermanagmentapplication.exception.UserNotFoundException;
import gr.aueb.cf.customermanagmentapplication.model.Customer;
import gr.aueb.cf.customermanagmentapplication.model.User;
import gr.aueb.cf.customermanagmentapplication.repository.ICustomerRepository;
import gr.aueb.cf.customermanagmentapplication.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;
    private final IUserRepository userRepository;

    @Autowired
    public CustomerService(ICustomerRepository customerRepository, IUserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    public Customer addCustomer(User user, CustomerDTO customerDTO) {
        Customer customerToBeSaved = new Customer(
                null,
                customerDTO.getTaxIdNumber(),
                customerDTO.getFirstname(),
                customerDTO.getLastname(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getCity(),
                customerDTO.getAddress(),
                customerDTO.getOrderDate(),
                customerDTO.getDeliveryDate(),
                customerDTO.getPaymentInAdvance(),
                customerDTO.getTotalAmount(),
                customerDTO.getBalance(),
                UUID.randomUUID().toString(),
                user);
        customerToBeSaved.setUser(user);
        return customerRepository.save(customerToBeSaved);
    }



    public List<Customer> findAllCustomers(Long id) {
        return customerRepository.findAllByUserId(id);
    }

    public Customer updateCustomer(User user, CustomerDTO customerDTO) {
        Customer customerToBeSaved = new Customer(
                customerDTO.getId(),
                customerDTO.getTaxIdNumber(),
                customerDTO.getFirstname(),
                customerDTO.getLastname(),
                customerDTO.getEmail(),
                customerDTO.getPhoneNumber(),
                customerDTO.getCity(),
                customerDTO.getAddress(),
                customerDTO.getOrderDate(),
                customerDTO.getDeliveryDate(),
                customerDTO.getPaymentInAdvance(),
                customerDTO.getTotalAmount(),
                customerDTO.getBalance(),
                UUID.randomUUID().toString(),
                user);
        customerToBeSaved.setUser(user);
        return customerRepository.save(customerToBeSaved);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer by id " + id + " was not found"));
    }

    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.deleteCustomerById(id);
    }
}

