package com.finflow.arisan_digital.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ArisanGroup {
    
    private Long id;
    private String name;
    private BigDecimal paymentAmount;
    private LocalDate startDate;
    private ArisanStatus status;
    private List<ArisanMember> members;
    private List<DrawResult> drawResults;
    private int totalSlots;
    private int currentSlot;

}

