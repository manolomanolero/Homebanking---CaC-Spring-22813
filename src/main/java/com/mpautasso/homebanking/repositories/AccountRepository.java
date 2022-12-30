package com.mpautasso.homebanking.repositories;

import com.mpautasso.homebanking.models.Account;
import com.mpautasso.homebanking.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findAllByUser(User user);
    Optional<Account> findByNumber(Integer number);
    Optional<Account> findByCbu(Integer cbu);
}
