package com.openbanking.yos.domain.payment.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.enums.PaymentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import com.openbanking.yos.domain.payment.dto.request.PaymentRequest;
import com.openbanking.yos.domain.payment.dto.response.PaymentResponse;
import com.openbanking.yos.domain.payment.entity.PaymentEntity;
import com.openbanking.yos.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ConsentRepository consentRepository;

    @Override
    public PaymentResponse createPayment(PaymentRequest request,
                                         String xRequestId,
                                         String xAspspCode,
                                         String xTppCode) {

        if (!request.getKatilimciBlg().getHhsKod().equals(xAspspCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        if (!request.getKatilimciBlg().getYosKod().equals(xTppCode)) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        ConsentEntity consentEntity = consentRepository.findByRizaNo(request.getRzBlg().getRizaNo())
                .orElseThrow(() -> new OhvpsException("TR.OHVPS.Resource.NotFound"));

        if (consentEntity.getRizaDrm() != ConsentStatus.Y) {
            throw new OhvpsException("TR.OHVPS.Resource.NotFound");
        }

        if (!consentEntity.getHhsKod().equals(request.getKatilimciBlg().getHhsKod())) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidASPSP");
        }

        if (!consentEntity.getYosKod().equals(request.getKatilimciBlg().getYosKod())) {
            throw new OhvpsException("TR.OHVPS.Connection.InvalidTPP");
        }

        PaymentRequest.OdemeBaslatma odmBsltm = request.getOdmBsltm();

        PaymentEntity entity = PaymentEntity.builder()
                .odmEmriNo(UUID.randomUUID().toString())
                .rizaNo(consentEntity.getRizaNo())
                .odmDrm(PaymentStatus.G)
                .hhsKod(consentEntity.getHhsKod())
                .yosKod(consentEntity.getYosKod())
                .yetYntm(consentEntity.getYetYntm())
                .yonAdr(consentEntity.getYonAdr())
                .hhsYonAdr(consentEntity.getHhsYonAdr())
                .ohkTur(odmBsltm.getKmlk().getOhkTur())
                .kmlkTur(odmBsltm.getKmlk().getKmlkTur())
                .kmlkVrs(odmBsltm.getKmlk().getKmlkVrs())
                .prBrm(odmBsltm.getIslTtr().getPrBrm())
                .ttr(odmBsltm.getIslTtr().getTtr())
                .gonUnv(odmBsltm.getGon() != null ? odmBsltm.getGon().getUnv() : null)
                .gonHspNo(odmBsltm.getGon() != null ? odmBsltm.getGon().getHspNo() : null)
                .alcUnv(odmBsltm.getAlc().getUnv())
                .alcHspNo(odmBsltm.getAlc().getHspNo())
                .odmKynk(odmBsltm.getOdmAyr().getOdmKynk())
                .odmAmc(odmBsltm.getOdmAyr().getOdmAmc())
                .refBlg(odmBsltm.getOdmAyr().getRefBlg())
                .odmAcklm(odmBsltm.getOdmAyr().getOdmAcklm())
                .build();

        consentEntity.setRizaDrm(ConsentStatus.K);
        consentRepository.save(consentEntity);

        PaymentEntity savedPayment = paymentRepository.save(entity);

        return toResponse(savedPayment, consentEntity);
    }

    private PaymentResponse toResponse(PaymentEntity entity, ConsentEntity consent) {

        PaymentResponse.RizaBilgileri rzBlg = PaymentResponse.RizaBilgileri.builder()
                .rizaNo(consent.getRizaNo())
                .olusZmn(consent.getOlusZmn())
                .gnclZmn(consent.getGnclZmn())
                .rizaDrm(consent.getRizaDrm())
                .build();

        PaymentResponse.KatilimciBilgisi katilimciBlg = PaymentResponse.KatilimciBilgisi.builder()
                .hhsKod(entity.getHhsKod())
                .yosKod(entity.getYosKod())
                .build();

        PaymentResponse.Gkd gkd = PaymentResponse.Gkd.builder()
                .yetYntm(entity.getYetYntm() != null ? entity.getYetYntm().name() : null)
                .yonAdr(entity.getYonAdr())
                .hhsYonAdr(entity.getHhsYonAdr())
                .build();

        PaymentResponse.Kimlik kmlk = PaymentResponse.Kimlik.builder()
                .kmlkTur(entity.getKmlkTur())
                .kmlkVrs(entity.getKmlkVrs())
                .ohkTur(entity.getOhkTur())
                .build();

        PaymentResponse.Tutar islTtr = PaymentResponse.Tutar.builder()
                .prBrm(entity.getPrBrm())
                .ttr(entity.getTtr())
                .build();

        PaymentResponse.Hesap gon = (entity.getGonHspNo() != null || entity.getGonUnv() != null)
                ? PaymentResponse.Hesap.builder()
                        .unv(entity.getGonUnv())
                        .hspNo(entity.getGonHspNo())
                        .build()
                : null;

        PaymentResponse.Hesap alc = PaymentResponse.Hesap.builder()
                .unv(entity.getAlcUnv())
                .hspNo(entity.getAlcHspNo())
                .build();

        PaymentResponse.OdemeAyrintilari odmAyr = PaymentResponse.OdemeAyrintilari.builder()
                .odmKynk(entity.getOdmKynk())
                .odmAmc(entity.getOdmAmc())
                .refBlg(entity.getRefBlg())
                .odmAcklm(entity.getOdmAcklm())
                .build();

        PaymentResponse.OdemeBaslatma odmBsltm = PaymentResponse.OdemeBaslatma.builder()
                .kmlk(kmlk)
                .islTtr(islTtr)
                .gon(gon)
                .alc(alc)
                .odmAyr(odmAyr)
                .build();

        PaymentResponse.OdemeBilgileri odmBlg = PaymentResponse.OdemeBilgileri.builder()
                .odmEmriNo(entity.getOdmEmriNo())
                .olusZmn(entity.getOlusZmn())
                .gnclZmn(entity.getGnclZmn())
                .odmDrm(entity.getOdmDrm())
                .build();

        return PaymentResponse.builder()
                .rzBlg(rzBlg)
                .katilimciBlg(katilimciBlg)
                .gkd(gkd)
                .odmBsltm(odmBsltm)
                .odmBlg(odmBlg)
                .build();
    }
}

