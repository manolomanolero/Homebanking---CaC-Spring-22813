package com.mpautasso.homebanking.repositories;

import com.mpautasso.homebanking.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
