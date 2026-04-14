package com.openbanking.yos.domain.card.repository;

import com.openbanking.yos.domain.card.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {

    List<CardEntity> findByRizaNo(String rizaNo);

    Optional<CardEntity> findByKartRef(String kartRef);
}

