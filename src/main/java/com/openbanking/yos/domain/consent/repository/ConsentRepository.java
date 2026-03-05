package com.openbanking.yos.domain.consent.repository;

import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsentRepository extends JpaRepository<ConsentEntity, Long> {

    Optional<ConsentEntity> findByRizaNo(String rizaNo);
}
