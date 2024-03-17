package com.loader.loader.repositories;

import com.loader.loader.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRespository extends JpaRepository<Account, Integer> {
}
