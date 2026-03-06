package com.openbanking.yos.domain.consent.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsentServiceImpl implements ConsentService {

    private final ConsentRepository consentRepository;

    @Override
    public ConsentResponse createConsent(ConsentRequest request,
                                         String xRequestId,
                                         String xAspspCode,
                                         String xTppCode) {

        // 1. HHS kodu doğrulama
        if (!request.getKatilimciBlg().getHhsKod().equals(xAspspCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        // 2. YOS kodu doğrulama
        if (!request.getKatilimciBlg().getYosKod().equals(xTppCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        // 3. Ödeme kaynağı doğrulama
        if (!"O".equals(request.getOdmBsltm().getOdmAyr().getOdmKynk())) {
            throw new OhvpsException("TR.OHVPS.Resource.InvalidFormat");
        }

        // 4. Ayrık GKD kontrolü — yetYntm "A" ise yönlendirme adresi zorunlu değil ama
        //    yonAdr null olmamalı (ayrikGkd senaryosu)
        if ("A".equals(request.getGkd().getYetYntm()) && request.getGkd().getYonAdr() == null) {
            throw new OhvpsException("TR.OHVPS.Resource.InvalidFormat");
        }

        // 5. İdempotency: aynı xRequestId ile B durumunda kayıt varsa mevcut response dön
        Optional<ConsentEntity> existingOpt = consentRepository.findByRizaNo(xRequestId);
        if (existingOpt.isPresent() && existingOpt.get().getRizaDrm() == ConsentStatus.B) {
            return toResponse(existingOpt.get());
        }

        // 6. Yeni entity oluştur — alanları manuel map et
        ConsentRequest.OdemeBaslatma odmBsltm = request.getOdmBsltm();

        ConsentEntity entity = ConsentEntity.builder()
                .rizaNo(UUID.randomUUID().toString())
                .rizaDrm(ConsentStatus.B)
                .yetTmmZmn(LocalDateTime.now().plusMinutes(5))
                // Katılımcı bilgileri
                .hhsKod(request.getKatilimciBlg().getHhsKod())
                .yosKod(request.getKatilimciBlg().getYosKod())
                // GKD
                .yetYntm(request.getGkd().getYetYntm() != null
                        ? com.openbanking.yos.common.enums.GkdType.valueOf(request.getGkd().getYetYntm())
                        : null)
                .yonAdr(request.getGkd().getYonAdr())
                // Kimlik
                .ohkTur(odmBsltm.getKmlk().getOhkTur())
                .kmlkTur(odmBsltm.getKmlk().getKmlkTur())
                .kmlkVrs(odmBsltm.getKmlk().getKmlkVrs())
                // Tutar
                .prBrm(odmBsltm.getIslTtr().getPrBrm())
                .ttr(odmBsltm.getIslTtr().getTtr())
                // Gönderen (nullable)
                .gonUnv(odmBsltm.getGon() != null ? odmBsltm.getGon().getUnv() : null)
                .gonHspNo(odmBsltm.getGon() != null ? odmBsltm.getGon().getHspNo() : null)
                // Alıcı
                .alcUnv(odmBsltm.getAlc().getUnv())
                .alcHspNo(odmBsltm.getAlc().getHspNo())
                // Ödeme ayrıntıları
                .odmKynk(odmBsltm.getOdmAyr().getOdmKynk())
                .odmAmc(odmBsltm.getOdmAyr().getOdmAmc())
                .refBlg(odmBsltm.getOdmAyr().getRefBlg())
                .build();

        // 7. Kaydet
        ConsentEntity saved = consentRepository.save(entity);

        return toResponse(saved);
    }

    @Override
    public ConsentResponse getConsent(String rizaNo, String xAspspCode, String xTppCode) {
        ConsentEntity entity = consentRepository.findByRizaNo(rizaNo)
                .orElseThrow(() -> new OhvpsException("TR.OHVPS.Resource.NotFound"));

        if (!entity.getHhsKod().equals(xAspspCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        if (!entity.getYosKod().equals(xTppCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        if (entity.getRizaDrm() == ConsentStatus.K || entity.getRizaDrm() == ConsentStatus.I) {
            throw new OhvpsException("TR.OHVPS.Resource.InvalidStatus");
        }

        return toResponse(entity);
    }

    // -----------------------------------------------------------------------
    // Yardımcı: Entity → ConsentResponse
    // -----------------------------------------------------------------------
    private ConsentResponse toResponse(ConsentEntity entity) {

        ConsentResponse.RizaBilgileri rzBlg = ConsentResponse.RizaBilgileri.builder()
                .rizaNo(entity.getRizaNo())
                .olusZmn(entity.getOlusZmn())
                .gnclZmn(entity.getGnclZmn())
                .rizaDrm(entity.getRizaDrm())
                .rizaIptDtyKod(entity.getRizaIptDtyKod())
                .build();

        ConsentResponse.KatilimciBilgisi katilimciBlg = ConsentResponse.KatilimciBilgisi.builder()
                .hhsKod(entity.getHhsKod())
                .yosKod(entity.getYosKod())
                .build();

        ConsentResponse.Gkd gkd = ConsentResponse.Gkd.builder()
                .yetYntm(entity.getYetYntm() != null ? entity.getYetYntm().name() : null)
                .yonAdr(entity.getYonAdr())
                .hhsYonAdr(entity.getHhsYonAdr())
                .yetTmmZmn(entity.getYetTmmZmn())
                .build();

        ConsentResponse.Kimlik kmlk = ConsentResponse.Kimlik.builder()
                .ohkTur(entity.getOhkTur())
                .kmlkTur(entity.getKmlkTur())
                .kmlkVrs(entity.getKmlkVrs())
                .build();

        ConsentResponse.Tutar islTtr = ConsentResponse.Tutar.builder()
                .prBrm(entity.getPrBrm())
                .ttr(entity.getTtr())
                .build();

        ConsentResponse.Hesap gon = (entity.getGonHspNo() != null || entity.getGonUnv() != null)
                ? ConsentResponse.Hesap.builder()
                        .unv(entity.getGonUnv())
                        .hspNo(entity.getGonHspNo())
                        .build()
                : null;

        ConsentResponse.Hesap alc = ConsentResponse.Hesap.builder()
                .unv(entity.getAlcUnv())
                .hspNo(entity.getAlcHspNo())
                .build();

        ConsentResponse.OdemeAyrintilari odmAyr = ConsentResponse.OdemeAyrintilari.builder()
                .odmKynk(entity.getOdmKynk())
                .odmAmc(entity.getOdmAmc())
                .refBlg(entity.getRefBlg())
                .build();

        ConsentResponse.OdemeBaslatma odmBsltm = ConsentResponse.OdemeBaslatma.builder()
                .kmlk(kmlk)
                .islTtr(islTtr)
                .gon(gon)
                .alc(alc)
                .odmAyr(odmAyr)
                .build();

        return ConsentResponse.builder()
                .rzBlg(rzBlg)
                .katilimciBlg(katilimciBlg)
                .gkd(gkd)
                .odmBsltm(odmBsltm)
                .build();
    }
}
