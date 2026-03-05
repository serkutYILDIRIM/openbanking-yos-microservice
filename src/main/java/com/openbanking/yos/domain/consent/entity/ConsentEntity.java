package com.openbanking.yos.domain.consent.entity;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.GkdType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ohvps_rizasi")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String rizaNo;

    @Column(length = 4)
    private String hhsKod;

    @Column(length = 4)
    private String yosKod;

    @Enumerated(EnumType.STRING)
    private ConsentStatus rizaDrm;

    @Column(length = 2)
    private String rizaIptDtyKod;

    @CreationTimestamp
    private LocalDateTime olusZmn;

    @UpdateTimestamp
    private LocalDateTime gnclZmn;

    private LocalDateTime yetTmmZmn;

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
}
