package com.micro.ecommerce.ecommerce.api.controller.auth;

import com.micro.ecommerce.ecommerce.api.model.RegistrationBody;
import com.micro.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import com.micro.ecommerce.ecommerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling authentication requests.
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    /** The user service. */
    private final UserService userService;

    /**
     * Spring injected constructor.
     * @param userService
     */
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Post Mapping to handle registering users.
     * @param registrationBody The registration information.
     * @return Response to front end.
     */
    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok("User registered successfully.");
        } catch (UserAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}