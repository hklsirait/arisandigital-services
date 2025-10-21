package com.finflow.arisan_digital.infra.db.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "arisan_members", 
       indexes = @Index(name = "idx_user_group", columnList = "arisan_group_id, userId", unique = true))
@Data
@NoArgsConstructor
public class ArisanMemberEntity {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean hasWon = false;
    
    private LocalDate joinDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arisan_group_id", nullable = false)
    private ArisanGroupEntity arisanGroup;

    @OneToMany(mappedBy = "arisanMember", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PaymentEntity> payments = new ArrayList<>();
}
