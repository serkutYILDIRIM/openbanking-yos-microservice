package com.openbanking.yos.domain.payment.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentMapperTest {

    @Test
    void shouldCreateMapper() {
        PaymentMapper mapper = new PaymentMapper() {};
        assertNotNull(mapper);
    }
}
