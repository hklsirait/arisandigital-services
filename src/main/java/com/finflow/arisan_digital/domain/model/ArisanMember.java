package com.finflow.arisan_digital.domain.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ArisanMember {
    private Long id;
    private String userId; 
    private String name;
    private boolean hasWon;
    private LocalDate joinDate;
    private ArisanGroup arisanGroup;
    private List<Payment> payments;
}
