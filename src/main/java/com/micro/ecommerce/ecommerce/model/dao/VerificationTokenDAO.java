package com.micro.ecommerce.ecommerce.model.dao;

import com.micro.ecommerce.ecommerce.model.LocalUser;
import com.micro.ecommerce.ecommerce.model.VerificationToken;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

/**
 * Data Access Object for the VerificationToken data.
 */
public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    void deleteByUser(LocalUser user);

}
