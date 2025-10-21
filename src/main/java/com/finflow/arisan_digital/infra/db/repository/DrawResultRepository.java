package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.infra.db.entity.DrawResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrawResultRepository extends JpaRepository<DrawResultEntity, Long> {
    
    List<DrawResultEntity> findByArisanGroupIdOrderByDrawDateDesc(Long groupId);
}
