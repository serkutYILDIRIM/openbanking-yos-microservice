package com.openbanking.yos.domain.card.service;

import com.openbanking.yos.common.enums.ConsentStatus;
import com.openbanking.yos.common.exception.OhvpsException;
import com.openbanking.yos.domain.card.dto.response.CardListResponse;
import com.openbanking.yos.domain.card.dto.response.CardResponse;
import com.openbanking.yos.domain.card.entity.CardEntity;
import com.openbanking.yos.domain.card.repository.CardRepository;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import com.openbanking.yos.domain.consent.repository.ConsentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private ConsentRepository consentRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    private static final String RIZA_NO = "riza-123";
    private static final String HHS_KOD = "HHS01";
    private static final String YOS_KOD = "YOS01";
    private static final String KART_REF = "kart-456";

    private ConsentEntity validConsent;
    private CardEntity validCard;

    @BeforeEach
    void setUp() {
        validConsent = ConsentEntity.builder()
                .rizaNo(RIZA_NO)
                .hhsKod(HHS_KOD)
                .yosKod(YOS_KOD)
                .rizaDrm(ConsentStatus.E)
                .build();

        validCard = CardEntity.builder()
                .rizaNo(RIZA_NO)
                .kartRef(KART_REF)
                .kartNo("123456******1234")
                .kartTipi("K")
                .altKartTipi("B")
                .kartFormu("F")
                .kartTuru("T")
                .kartStatu("A")
                .kartSahibi("John Doe")
                .kartUrunAdi("Gold Card")
                .kartSema("V")
                .build();
    }

    @Test
    @DisplayName("Tüm kartları başarıyla getir")
    void getCards_WhenValidRequest_ShouldReturnCardList() {
        // Given
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));
        when(cardRepository.findByRizaNo(RIZA_NO)).thenReturn(List.of(validCard));

        // When
        CardListResponse response = cardService.getCards(HHS_KOD, YOS_KOD, RIZA_NO);

        // Then
        assertNotNull(response);
        assertEquals(1, response.getKartlar().size());
        assertEquals(KART_REF, response.getKartlar().get(0).getKartRef());
        verify(consentRepository).findByRizaNo(RIZA_NO);
        verify(cardRepository).findByRizaNo(RIZA_NO);
    }

    @Test
    @DisplayName("Rıza bulunamadığında hata fırlat")
    void getCards_WhenConsentNotFound_ShouldThrowException() {
        // Given
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.empty());

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCards(HHS_KOD, YOS_KOD, RIZA_NO));
        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    @DisplayName("HHS kodu uyuşmadığında hata fırlat")
    void getCards_WhenAspspCodeMismatch_ShouldThrowException() {
        // Given
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCards("WRONG_HHS", YOS_KOD, RIZA_NO));
        assertEquals("TR.OHVPS.Connection.InvalidASPSP", exception.getMessage());
    }

    @Test
    @DisplayName("YOS kodu uyuşmadığında hata fırlat")
    void getCards_WhenTppCodeMismatch_ShouldThrowException() {
        // Given
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCards(HHS_KOD, "WRONG_YOS", RIZA_NO));
        assertEquals("TR.OHVPS.Connection.InvalidTPP", exception.getMessage());
    }

    @Test
    @DisplayName("Rıza durumu uygun olmadığında hata fırlat")
    void getCards_WhenConsentStatusInvalid_ShouldThrowException() {
        // Given
        validConsent.setRizaDrm(ConsentStatus.I); // İptal edilmiş rıza
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCards(HHS_KOD, YOS_KOD, RIZA_NO));
        assertEquals("TR.OHVPS.Resource.ConsentMismatch", exception.getMessage());
    }

    @Test
    @DisplayName("Belirli bir kartı başarıyla getir")
    void getCard_WhenValidRequest_ShouldReturnCard() {
        // Given
        when(cardRepository.findByKartRef(KART_REF)).thenReturn(Optional.of(validCard));
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));

        // When
        CardResponse response = cardService.getCard(KART_REF, HHS_KOD, YOS_KOD, RIZA_NO);

        // Then
        assertNotNull(response);
        assertEquals(KART_REF, response.getKartRef());
        assertEquals(RIZA_NO, response.getRizaNo());
    }

    @Test
    @DisplayName("Kart bulunamadığında hata fırlat")
    void getCard_WhenCardNotFound_ShouldThrowException() {
        // Given
        when(cardRepository.findByKartRef(KART_REF)).thenReturn(Optional.empty());

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCard(KART_REF, HHS_KOD, YOS_KOD, RIZA_NO));
        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }

    @Test
    @DisplayName("Kartın rıza numarası uyuşmadığında hata fırlat")
    void getCard_WhenCardConsentMismatch_ShouldThrowException() {
        // Given
        validCard.setRizaNo("another-riza");
        when(cardRepository.findByKartRef(KART_REF)).thenReturn(Optional.of(validCard));
        when(consentRepository.findByRizaNo(RIZA_NO)).thenReturn(Optional.of(validConsent));

        // When & Then
        OhvpsException exception = assertThrows(OhvpsException.class,
                () -> cardService.getCard(KART_REF, HHS_KOD, YOS_KOD, RIZA_NO));
        assertEquals("TR.OHVPS.Resource.NotFound", exception.getMessage());
    }
}
