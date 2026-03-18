package com.openbanking.yos.domain.payment.entity;

import com.openbanking.yos.common.enums.GkdType;
import com.openbanking.yos.common.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ohvps_odeme")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String odmEmriNo;

    @Column(nullable = false)
    private String rizaNo;

    @Enumerated(EnumType.STRING)
    private PaymentStatus odmDrm;

    @Column(length = 4)
    private String hhsKod;

    @Column(length = 4)
    private String yosKod;

    @CreationTimestamp
    private LocalDateTime olusZmn;

    @UpdateTimestamp
    private LocalDateTime gnclZmn;

    @Enumerated(EnumType.STRING)
    private GkdType yetYntm;

    @Column(length = 1024)
    private String yonAdr;

    @Column(length = 1024)
    private String hhsYonAdr;

    @Column(length = 1)
    private String ohkTur;

    @Column(length = 1)
    private String kmlkTur;

    @Column(length = 30)
    private String kmlkVrs;

    @Column(length = 3)
    private String prBrm;

    @Column(length = 24)
    private String ttr;

    @Column(length = 140)
    private String gonUnv;

    @Column(length = 26)
    private String gonHspNo;

    @Column(length = 140, nullable = false)
    private String alcUnv;

    @Column(length = 26, nullable = false)
    private String alcHspNo;

    @Column(length = 1, nullable = false)
    private String odmKynk;

    @Column(length = 2, nullable = false)
    private String odmAmc;

    @Column(length = 140)
    private String refBlg;

    @Column(length = 200)
    private String odmAcklm;
}

