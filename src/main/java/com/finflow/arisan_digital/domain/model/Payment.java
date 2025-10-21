package com.finflow.arisan_digital.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class Payment {
    private Long id;
    private ArisanMember member;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
}
