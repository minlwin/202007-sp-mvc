package com.jdc.online.model.repo;

import java.util.Optional;

import com.jdc.online.model.BaseRepository;
import com.jdc.online.model.entity.Account;

public interface AccountRepo extends BaseRepository<Account, Integer>{

	Optional<Account> findOneByEmail(String email);
}
