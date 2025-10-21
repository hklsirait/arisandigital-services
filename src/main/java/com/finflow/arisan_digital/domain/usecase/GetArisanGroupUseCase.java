package com.finflow.arisan_digital.domain.usecase;

import com.finflow.arisan_digital.domain.model.ArisanGroup;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Penting untuk read-only

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetArisanGroupUseCase {
    private final ArisanGroupRepository repository;

    // findAll()
    @Transactional(readOnly = true) // Transaksi read-only lebih cepat
    public List<ArisanGroup> getAll() {
        // 1. Panggil repository (dapat List<ArisanGroupEntity>)
        // 2. Ubah (map) setiap entity menjadi domain model
        return repository.findAll().stream()
                .map(this::mapToDomain) // Panggil mapper
                .collect(Collectors.toList());
    }

    // findById(Long id)
    @Transactional(readOnly = true)
    public Optional<ArisanGroup> getById(Long id) { // ID harus Long, bukan String
        // 1. Panggil repository (dapat Optional<ArisanGroupEntity>)
        // 2. Jika ada, map isinya ke domain model
        return repository.findById(id)
                .map(this::mapToDomain); // map() milik Optional
    }

    // Mapper helper
    // Ini adalah logika untuk mengubah Entity (data DB) ke Domain Model (logika bisnis)
    private ArisanGroup mapToDomain(ArisanGroupEntity entity) {
        return ArisanGroup.builder()
                .id(entity.getId())
                .name(entity.getName())
                .paymentAmount(entity.getPaymentAmount())
                .startDate(entity.getStartDate())
                .status(entity.getStatus())
                .totalSlots(entity.getTotalSlots())
                .currentSlot(entity.getCurrentSlot())
                // TODO: Mapping 'members' dan 'drawResults' jika dibutuhkan
                // Ini bisa jadi 'lazy loading', jadi hati-hati
                .build();
    }
}
