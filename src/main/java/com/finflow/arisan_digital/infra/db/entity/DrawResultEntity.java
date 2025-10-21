package com.finflow.arisan_digital.infra.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "draw_results")
@Data
@NoArgsConstructor
public class DrawResultEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate drawDate;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amountWon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_member_id", referencedColumnName = "id", nullable = false)
    private ArisanMemberEntity winner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arisan_group_id", nullable = false)
    private ArisanGroupEntity arisanGroup;
}
