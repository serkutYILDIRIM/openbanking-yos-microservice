package com.openbanking.yos.domain.consent.mapper;

import com.openbanking.yos.domain.consent.dto.request.ConsentRequest;
import com.openbanking.yos.domain.consent.dto.response.ConsentResponse;
import com.openbanking.yos.domain.consent.entity.ConsentEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsentMapperTest {

    @Test
    void shouldCreateMapper() {
        ConsentMapper mapper = new ConsentMapper() {};
        assertNotNull(mapper);
    }

    @Test
    void shouldCreateConsentRequest() {
        ConsentRequest request = new ConsentRequest();
        assertNotNull(request);
    }

    @Test
    void shouldCreateConsentResponse() {
        ConsentResponse response = new ConsentResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateConsentEntity() {
        ConsentEntity entity = new ConsentEntity();
        assertNotNull(entity);
    }
}
