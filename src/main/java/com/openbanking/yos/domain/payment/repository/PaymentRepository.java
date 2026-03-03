package com.openbanking.yos.domain.payment.repository;

import com.openbanking.yos.domain.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
}

