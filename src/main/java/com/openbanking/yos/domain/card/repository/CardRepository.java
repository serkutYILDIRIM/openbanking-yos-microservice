package com.openbanking.yos.domain.card.repository;

import com.openbanking.yos.domain.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}

