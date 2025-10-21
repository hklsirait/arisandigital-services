package com.finflow.arisan_digital.domain.usecase;

import com.finflow.arisan_digital.domain.model.ArisanGroup;
import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepository;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupRequest;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateArisanGroupUseCase {
    private final ArisanGroupRepository repository;

    private final ArisanGroupRepository arisanGroupRepository;

    @Transactional
    public ArisanGroupResponse execute(ArisanGroupRequest request) {
      
        ArisanGroupEntity entity = new ArisanGroupEntity();
        entity.setName(request.getName());
        entity.setPaymentAmount(request.getPaymentAmount());
        entity.setTotalSlots(request.getTotalSlots());
        
    
        entity.setCurrentSlot(0);
        entity.setStatus(ArisanStatus.PENDING); 

        ArisanGroupEntity savedEntity = arisanGroupRepository.save(entity);

     
        return mapToResponse(savedEntity);
    }

    private ArisanGroupResponse mapToResponse(ArisanGroupEntity entity) {
        return ArisanGroupResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .paymentAmount(entity.getPaymentAmount())
                .startDate(entity.getStartDate())
                .status(entity.getStatus())
                .totalSlots(entity.getTotalSlots())
                .currentSlot(entity.getCurrentSlot())
                .build();
    }

}
