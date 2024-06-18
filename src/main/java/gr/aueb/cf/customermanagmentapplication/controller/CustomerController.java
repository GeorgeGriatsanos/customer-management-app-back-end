package gr.aueb.cf.customermanagmentapplication.controller;

import gr.aueb.cf.customermanagmentapplication.dto.CustomerDTO;
import gr.aueb.cf.customermanagmentapplication.model.Customer;
import gr.aueb.cf.customermanagmentapplication.model.User;
import gr.aueb.cf.customermanagmentapplication.service.CustomerService;
import gr.aueb.cf.customermanagmentapplication.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final IUserService userService;

    public CustomerController(CustomerService customerService, IUserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Customer.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam String userId) {
        try {
            Long userIdLong = Long.parseLong(userId);
            List<Customer> customers = customerService.findAllCustomers(userIdLong);
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Get a customer by ID", description = "Retrieve a customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved customer",
                    content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/find/{id}")
    public ResponseEntity<Customer> getCustomerById(
            @Parameter(description = "ID of the customer to retrieve", required = true)
            @PathVariable("id") Long id) {
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @Operation(summary = "Add a new customer", description = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created customer",
                    content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(
            @Parameter(description = "Customer object to be added", required = true)
            @RequestBody CustomerDTO customerDTO) {
        try {
            User user = userService.getUserById(Long.parseLong(customerDTO.getUserId()));
            if (user != null) {
                Customer newCustomer = customerService.addCustomer(user, customerDTO);
                return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update an existing customer", description = "Update the details of an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated customer",
                    content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(
            @Parameter(description = "Customer object to be updated", required = true)
            @RequestBody CustomerDTO customerDTO) {
        User user = userService.getUserById(Long.parseLong(customerDTO.getUserId()));
        if (user != null) {
            Customer newCustomer = customerService.updateCustomer(user, customerDTO);
            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @Operation(summary = "Delete a customer", description = "Delete a customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted customer"),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID of the customer to be deleted", required = true)
            @PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
