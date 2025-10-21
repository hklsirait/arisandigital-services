
package com.finflow.arisan_digital.infra.rest.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class DrawResultResponse {
    private Long id;
    private LocalDate drawDate;
    private BigDecimal amountWon;
    private String winnerName;
    private String winnerUserId;
}