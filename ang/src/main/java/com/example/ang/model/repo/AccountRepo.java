package com.example.ang.model.repo;

import com.example.ang.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends CrudRepository<Account,Long> {
    @Query("Select balance from Account a where a.id =:id")
    Long getBalance(@Param("id") Long id);

}
