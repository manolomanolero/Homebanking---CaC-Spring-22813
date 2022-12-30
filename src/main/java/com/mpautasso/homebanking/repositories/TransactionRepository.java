package com.mpautasso.homebanking.repositories;

import com.mpautasso.homebanking.models.Account;
import com.mpautasso.homebanking.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByAccountReceiver(Account account);

    List<Transaction> findByAccountSender(Account account);
}
