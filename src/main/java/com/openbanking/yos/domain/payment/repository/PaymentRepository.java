package com.openbanking.yos.domain.payment.repository;

import com.openbanking.yos.domain.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    Optional<PaymentEntity> findByOdmEmriNo(String odmEmriNo);
}

