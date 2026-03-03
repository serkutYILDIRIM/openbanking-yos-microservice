package com.openbanking.yos.domain.account.repository;

import com.openbanking.yos.domain.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}

