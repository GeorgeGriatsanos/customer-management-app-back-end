package gr.aueb.cf.customermanagmentapplication.service;

import gr.aueb.cf.customermanagmentapplication.dto.LoginDTO;
import gr.aueb.cf.customermanagmentapplication.dto.UserDTO;
import gr.aueb.cf.customermanagmentapplication.model.User;
import gr.aueb.cf.customermanagmentapplication.response.LoginResponse;

import gr.aueb.cf.customermanagmentapplication.model.User;

public interface IUserService {
    User addUser(UserDTO userDTO);
    LoginResponse loginUser(LoginDTO loginDTO);
    User getUserById(Long userId); // Add this method declaration

    boolean existsByUsername(String username);

    boolean checkEmailExists(String email);
}

