package gr.aueb.cf.customermanagmentapplication.controller;

import gr.aueb.cf.customermanagmentapplication.dto.LoginDTO;
import gr.aueb.cf.customermanagmentapplication.dto.UserDTO;
import gr.aueb.cf.customermanagmentapplication.model.User;
import gr.aueb.cf.customermanagmentapplication.response.LoginResponse;
import gr.aueb.cf.customermanagmentapplication.service.CustomerService;
import gr.aueb.cf.customermanagmentapplication.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Register a new user", description = "Create a new user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created user"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/register")
    public ResponseEntity<Boolean> addUser(
            @Parameter(description = "User object to be added", required = true)
            @RequestBody UserDTO userDTO) {
        User newUser = userService.addUser(userDTO);
        if (newUser != null) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Login a user", description = "Authenticate a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in"),
            @ApiResponse(responseCode = "400", description = "Invalid login credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Parameter(description = "Login credentials", required = true)
            @RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = userService.loginUser(loginDTO);
        if (loginResponse.isSuccess()) {
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(loginResponse, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<Boolean> checkUsernameExists(@PathVariable String username) {
        boolean exists = userService.existsByUsername(username);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/check-email/{email}")
    public boolean checkEmailExists(@PathVariable String email) {
        return userService.checkEmailExists(email);
    }
}
