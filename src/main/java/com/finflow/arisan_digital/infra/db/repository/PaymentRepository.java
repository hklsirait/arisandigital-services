package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.infra.db.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    
    List<PaymentEntity> findByArisanMemberId(Long memberId);
}
