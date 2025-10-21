package com.finflow.arisan_digital.domain.usecase;

import com.finflow.arisan_digital.domain.exception.ArisanException;
import com.finflow.arisan_digital.domain.exception.ResourceNotFoundException;
import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.entity.ArisanMemberEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepository;
import com.finflow.arisan_digital.infra.db.repository.ArisanMemberRepository;
import com.finflow.arisan_digital.infra.rest.dto.MemberJoinRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class JoinArisanUseCase {
    private final ArisanGroupRepository arisanGroupRepository;
    private final ArisanMemberRepository arisanMemberRepository;

    @Transactional
    public void execute(Long groupId, MemberJoinRequest request) {
        ArisanGroupEntity group = arisanGroupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Grup arisan dengan ID " + groupId + " tidak ditemukan"));

        // Validasi bisnis
        if (group.getStatus() != ArisanStatus.PENDING) {
            throw new ArisanException("Grup arisan ini tidak lagi menerima anggota baru.");
        }
        if (group.getCurrentSlot() >= group.getTotalSlots()) {
            throw new ArisanException("Grup arisan sudah penuh.");
        }
        
        // Cek apakah user sudah join
        arisanMemberRepository.findByArisanGroupIdAndUserId(groupId, request.getUserId())
                .ifPresent(member -> {
                    throw new ArisanException("User " + request.getName() + " sudah terdaftar di grup ini.");
                });

        // Buat member baru
        ArisanMemberEntity member = new ArisanMemberEntity();
        member.setUserId(request.getUserId());
        member.setName(request.getName());
        member.setJoinDate(LocalDate.now());
        member.setHasWon(false);
        member.setArisanGroup(group);

        arisanMemberRepository.save(member);

        // Update slot di grup
        group.setCurrentSlot(group.getCurrentSlot() + 1);
        
        // Jika grup penuh, ubah status jadi ACTIVE
        if (group.getCurrentSlot() == group.getTotalSlots()) {
            group.setStatus(ArisanStatus.ACTIVE);
            group.setStartDate(LocalDate.now()); // Mulai arisan
        }
        
        arisanGroupRepository.save(group);
    }
}
