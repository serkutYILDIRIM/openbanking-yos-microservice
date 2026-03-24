package com.openbanking.yos.domain.account.repository;

import com.openbanking.yos.domain.account.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    List<AccountEntity> findByRizaNo(String rizaNo);

    Optional<AccountEntity> findByHspRef(String hspRef);
}

