package com.shop.rest.repository;

import com.shop.rest.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
