package com.finflow.arisan_digital.domain.usecase;

import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepository;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupRequest;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class) 
class CreateArisanGroupUseCaseTest {

 
    @InjectMocks 
    private CreateArisanGroupUseCase useCase;


    @Mock
    private ArisanGroupRepository arisanGroupRepository;

    @Test
    void testExecute_Success() {
    
        ArisanGroupRequest request = new ArisanGroupRequest();
        request.setName("Arisan Gaji");
        request.setPaymentAmount(BigDecimal.valueOf(100000));
        request.setTotalSlots(10);

   
        ArisanGroupEntity savedEntity = new ArisanGroupEntity();
        savedEntity.setId(1L);
        savedEntity.setName("Arisan Gaji");
        savedEntity.setPaymentAmount(BigDecimal.valueOf(100000));
        savedEntity.setTotalSlots(10);
        savedEntity.setCurrentSlot(0);
        savedEntity.setStatus(ArisanStatus.PENDING); // Verifikasi logika default

        
        when(arisanGroupRepository.save(any(ArisanGroupEntity.class))).thenReturn(savedEntity);


        ArisanGroupResponse response = useCase.execute(request);

  
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Arisan Gaji");
        assertThat(response.getStatus()).isEqualTo(ArisanStatus.PENDING); 
        assertThat(response.getCurrentSlot()).isZero();
    }
}