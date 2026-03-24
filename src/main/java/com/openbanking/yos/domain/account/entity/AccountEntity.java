package com.openbanking.yos.domain.account.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ohvps_hesap")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 40)
    private String hspRef;

    @Column(nullable = false)
    private String rizaNo;

    @Column(length = 26)
    private String hspNo;

    @Column(nullable = false, length = 140)
    private String hspShb;

    @Column(length = 50)
    private String subeAdi;

    @Column(length = 50)
    private String kisaAd;

    @Column(nullable = false, length = 3)
    private String prBrm;

    @Column(nullable = false, length = 1)
    private String hspTur;

    @Column(nullable = false, length = 32)
    private String hspTip;

    @Column(length = 140)
    private String hspUrunAdi;

    @Column(nullable = false, length = 50)
    private String hspDrm;

    private LocalDateTime hspAclsTrh;

    @Column(length = 4)
    private String hhsKod;

    @Column(length = 4)
    private String yosKod;

    @CreationTimestamp
    private LocalDateTime olusZmn;

    @UpdateTimestamp
    private LocalDateTime gnclZmn;
}

