package com.finflow.arisan_digital.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class DrawResult {
    private Long id;
    private ArisanGroup arisanGroup;
    private ArisanMember winner;
    private LocalDate drawDate;
    private BigDecimal amountWon;
}
