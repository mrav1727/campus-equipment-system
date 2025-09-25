package edu.cit.veloso.miguelray.campusequipmentloan.controller;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.User;
import edu.cit.veloso.miguelray.campusequipmentloan.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String username,
                                         @RequestParam String password) {
        return ResponseEntity.ok(userService.register(username, password));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password) {
        if (userService.login(username, password)) {
            return ResponseEntity.ok("Login successful!");
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }
}
