package com.openbanking.yos.domain.card.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ohvps_kart")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rizaNo;

    @Column(unique = true, nullable = false, length = 40)
    private String kartRef;

    @Column(nullable = false, length = 16)
    private String kartNo;

    @Column(length = 16)
    private String asilKartNo;

    @Column(nullable = false, length = 1)
    private String kartTipi;

    @Column(nullable = false, length = 1)
    private String altKartTipi;

    @Column(nullable = false, length = 1)
    private String kartFormu;

    @Column(nullable = false, length = 1)
    private String kartTuru;

    @Column(nullable = false, length = 1)
    private String kartStatu;

    @Column(nullable = false, length = 140)
    private String kartSahibi;

    @Column(length = 140)
    private String kartTicariUnvan;

    @Column(nullable = false, length = 140)
    private String kartUrunAdi;

    @Column(length = 140)
    private String kartRumuz;

    @Column(nullable = false, length = 1)
    private String kartSema;

    @Column(length = 4)
    private String hhsKod;

    @Column(length = 4)
    private String yosKod;

    @CreationTimestamp
    private LocalDateTime olusZmn;

    @UpdateTimestamp
    private LocalDateTime gnclZmn;
}

