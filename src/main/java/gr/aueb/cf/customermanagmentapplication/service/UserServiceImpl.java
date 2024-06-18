package gr.aueb.cf.customermanagmentapplication.service;

import gr.aueb.cf.customermanagmentapplication.dto.LoginDTO;
import gr.aueb.cf.customermanagmentapplication.dto.UserDTO;
import gr.aueb.cf.customermanagmentapplication.model.User;
import gr.aueb.cf.customermanagmentapplication.response.LoginResponse;
import gr.aueb.cf.customermanagmentapplication.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepository.save(user);
        return user;
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        User user1 = userRepository.findByUsername(loginDTO.getUsername())
                .orElse(null);
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPassRight = passwordEncoder.matches(password, encodedPassword);
            if (isPassRight) {
                return new LoginResponse("Login Successfully", true, user1.getId().toString());
            } else {
                return new LoginResponse("Password not match", false, user1.getId().toString());
            }
        } else {
            return new LoginResponse("Username does not exist", false, null);
        }
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElse(null);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean checkEmailExists(String email) {
        // Check if email exists in database
        return userRepository.existsByEmail(email);
    }
}
