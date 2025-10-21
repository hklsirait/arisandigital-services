package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.infra.db.entity.ArisanMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArisanMemberRepository extends JpaRepository<ArisanMemberEntity, Long> {
    
    // Cari member yang belum menang di satu grup
    List<ArisanMemberEntity> findByArisanGroupIdAndHasWon(Long groupId, boolean hasWon);

    // Cari member spesifik di grup
    Optional<ArisanMemberEntity> findByArisanGroupIdAndUserId(Long groupId, String userId);
    
    // Hitung member yang belum menang
    long countByArisanGroupIdAndHasWon(Long groupId, boolean hasWon);
}
