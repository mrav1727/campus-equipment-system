package edu.cit.veloso.miguelray.campusequipmentloan.service;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.User;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // encode password
        return userRepository.save(user);
    }

    public boolean login(String username, String password) {
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isPresent()) {
            User user = optUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // ✅ use encoder
                user.setLoggedIn(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
