package com.finflow.arisan_digital.infra.rest.dto;

import com.finflow.arisan_digital.domain.model.ArisanStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder 
public class ArisanGroupResponse {
   private Long id;
    private String name;
    private BigDecimal paymentAmount;
    private LocalDate startDate;
    private ArisanStatus status;
    private int totalSlots;
    private int currentSlot;
   
    // private List<MemberSimpleResponse> members;
}
