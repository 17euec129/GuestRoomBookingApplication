package com.example.springboot;

import org.springframework.data.repository.CrudRepository;
import java.util.*;
// This will be AUTO IMPLEMENTED by Spring into a Bean called accountRepository
// CRUD refers Create, Read, Update, Delete

public interface AccountRepository extends CrudRepository<Account, Long> {
	 List<Account> findAllByUsername(String username);
	 List<Account> findAllByEmailId(String emailId);
	 List<Account> findAllByUsernameAndPassword(String username, String password);
	 Account getById(Long id);
}