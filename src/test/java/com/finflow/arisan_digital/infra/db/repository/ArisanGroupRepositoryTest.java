package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) 
class ArisanGroupRepositoryTest {

    @Autowired
    private ArisanGroupRepository arisanGroupRepository;

    @Test
    void testFindByStatus() {
       
        ArisanGroupEntity activeGroup = new ArisanGroupEntity();
        activeGroup.setName("Grup Aktif A");
        activeGroup.setPaymentAmount(BigDecimal.valueOf(100000));
        activeGroup.setTotalSlots(10);
        activeGroup.setCurrentSlot(10);
        activeGroup.setStatus(ArisanStatus.ACTIVE);
        arisanGroupRepository.save(activeGroup);

        ArisanGroupEntity pendingGroup = new ArisanGroupEntity();
        pendingGroup.setName("Grup Pending B");
        pendingGroup.setPaymentAmount(BigDecimal.valueOf(50000));
        pendingGroup.setTotalSlots(5);
        pendingGroup.setCurrentSlot(2);
        pendingGroup.setStatus(ArisanStatus.PENDING);
        arisanGroupRepository.save(pendingGroup);

        // Act (Eksekusi Method)
        List<ArisanGroupEntity> foundActive = arisanGroupRepository.findByStatus(ArisanStatus.ACTIVE);
        List<ArisanGroupEntity> foundPending = arisanGroupRepository.findByStatus(ArisanStatus.PENDING);
        
        // Assert (Verifikasi Hasil)
        assertThat(foundActive).hasSize(1);
        assertThat(foundActive.get(0).getName()).isEqualTo("Grup Aktif A");

        assertThat(foundPending).hasSize(1);
        assertThat(foundPending.get(0).getName()).isEqualTo("Grup Pending B");
    }
}