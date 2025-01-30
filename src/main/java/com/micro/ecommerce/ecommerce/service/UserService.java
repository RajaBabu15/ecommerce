package com.micro.ecommerce.ecommerce.service;

import com.micro.ecommerce.ecommerce.api.model.RegistrationBody;
import com.micro.ecommerce.ecommerce.exception.UserAlreadyExistsException;
import com.micro.ecommerce.ecommerce.model.LocalUser;
import com.micro.ecommerce.ecommerce.api.model.LoginBody;
import com.micro.ecommerce.ecommerce.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * Service for handling user actions.
 */
@Service
public class UserService {

    /** The LocalUserDAO. */
    private final LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    /**
     * Constructor injected by spring.
     */
    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }

    /**
     * Attempts to register a user given the information provided.
     * @param registrationBody The registration information.
     * @return The local user that has been written to the database.
     * @throws UserAlreadyExistsException Thrown if there is already a user with the given information.
     */
    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists.");
        }
        if (localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists.");
        }
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        return localUserDAO.save(user);
    }

    /**
     * Logins in a user and provides an authentication token back.
     * @param loginBody The login request.
     * @return The authentication token. Null if the request was invalid.
     */
    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if (opUser.isPresent()) {
            LocalUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }

}