package com.finflow.arisan_digital.infra.db.impl;

import com.finflow.arisan_digital.domain.model.ArisanStatus; 
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository; // Ini tidak wajib, tapi bagus

import java.util.List;
import java.util.Optional;

@Repository
public class ArisanGroupRepositoryImpl implements ArisanGroupRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ArisanGroupEntity> findByNameUsingCustomQuery(String name) {
   
        TypedQuery<ArisanGroupEntity> query = entityManager.createQuery(
                "SELECT g FROM ArisanGroupEntity g WHERE g.name = :groupName", 
                ArisanGroupEntity.class
        );
        query.setParameter("groupName", name);
        
        // Pakai getSingleResult() bisa error jika tidak ada, jadi pakai getResultList()
        List<ArisanGroupEntity> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }


}