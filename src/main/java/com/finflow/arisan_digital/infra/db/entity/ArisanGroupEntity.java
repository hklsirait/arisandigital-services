package com.finflow.arisan_digital.infra.db.entity;

import com.finflow.arisan_digital.domain.model.ArisanStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arisan_groups")
@Data
@NoArgsConstructor
public class ArisanGroupEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal paymentAmount;

    private LocalDate startDate;
    private int totalSlots;
    private int currentSlot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ArisanStatus status;

    @OneToMany(mappedBy = "arisanGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ArisanMemberEntity> members = new ArrayList<>();

    @OneToMany(mappedBy = "arisanGroup", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DrawResultEntity> drawResults = new ArrayList<>();
}
