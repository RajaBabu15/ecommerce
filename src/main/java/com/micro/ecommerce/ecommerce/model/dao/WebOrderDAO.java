package com.micro.ecommerce.ecommerce.model.dao;

import com.micro.ecommerce.ecommerce.model.LocalUser;
import com.micro.ecommerce.ecommerce.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

/**
 * Data Access Object to access WebOrder data.
 */
public interface WebOrderDAO extends ListCrudRepository<WebOrder, Long> {

    List<WebOrder> findByUser(LocalUser user);

}