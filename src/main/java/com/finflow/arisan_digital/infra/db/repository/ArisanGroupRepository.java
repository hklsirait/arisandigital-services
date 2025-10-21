package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.domain.model.ArisanGroup;
import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArisanGroupRepository extends JpaRepository<ArisanGroupEntity, Long>, 
        ArisanGroupRepositoryCustom {
   List<ArisanGroupEntity> findByStatus(ArisanStatus status);
}
