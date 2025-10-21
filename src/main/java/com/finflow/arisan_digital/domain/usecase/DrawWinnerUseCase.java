package com.finflow.arisan_digital.domain.usecase;

import com.finflow.arisan_digital.domain.exception.ArisanException;
import com.finflow.arisan_digital.domain.exception.ResourceNotFoundException;
import com.finflow.arisan_digital.domain.model.ArisanStatus;
import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import com.finflow.arisan_digital.infra.db.entity.ArisanMemberEntity;
import com.finflow.arisan_digital.infra.db.entity.DrawResultEntity;
import com.finflow.arisan_digital.infra.db.repository.ArisanGroupRepository;
import com.finflow.arisan_digital.infra.db.repository.ArisanMemberRepository;
import com.finflow.arisan_digital.infra.db.repository.DrawResultRepository;
import com.finflow.arisan_digital.infra.rest.dto.DrawResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DrawWinnerUseCase {
    private final ArisanGroupRepository arisanGroupRepository;
    private final ArisanMemberRepository arisanMemberRepository;
    private final DrawResultRepository drawResultRepository;
    private final Random random = new Random();

    @Transactional
    public DrawResultResponse execute(Long groupId) {
        ArisanGroupEntity group = arisanGroupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Grup arisan " + groupId + " tidak ditemukan."));

        // Validasi
        if (group.getStatus() != ArisanStatus.ACTIVE) {
            throw new ArisanException("Undian tidak bisa dilakukan. Status grup: " + group.getStatus());
        }

        // TODO: Tambahkan validasi pembayaran (semua member harus lunas utk periode ini)
        // besok kita gaskan

        // Panggil semua member yang belum menang
        List<ArisanMemberEntity> eligibleMembers = arisanMemberRepository.findByArisanGroupIdAndHasWon(groupId, false);

        if (eligibleMembers.isEmpty()) {
            throw new ArisanException("Semua anggota sudah menang. Arisan seharusnya sudah selesai.");
        }

        // Acak pemenang
        ArisanMemberEntity winner = eligibleMembers.get(random.nextInt(eligibleMembers.size()));
        
        // Update status pemenang
        winner.setHasWon(true);
        arisanMemberRepository.save(winner);

        // Hitung total hadiah
        BigDecimal amountWon = group.getPaymentAmount().multiply(new BigDecimal(group.getTotalSlots()));

        // Catat hasil undian
        DrawResultEntity drawResult = new DrawResultEntity();
        drawResult.setArisanGroup(group);
        drawResult.setWinner(winner);
        drawResult.setDrawDate(LocalDate.now());
        drawResult.setAmountWon(amountWon);
        DrawResultEntity savedResult = drawResultRepository.save(drawResult);

        // Cek apakah ini undian terakhir
        if (eligibleMembers.size() == 1) {
            // Ini adalah pemenang terakhir, tutup arisan
            closeArisan(group);
        }

        // Map ke Response DTO
        return DrawResultResponse.builder()
                .id(savedResult.getId())
                .drawDate(savedResult.getDrawDate())
                .amountWon(savedResult.getAmountWon())
                .winnerName(winner.getName())
                .winnerUserId(winner.getUserId())
                .build();
    }
    
    // Dipanggil oleh CloseArisanUseCase atau internal oleh DrawWinner
    @Transactional
    public void closeArisan(Long groupId) {
        ArisanGroupEntity group = arisanGroupRepository.findById(groupId)
            .orElseThrow(() -> new ResourceNotFoundException("Grup arisan " + groupId + " tidak ditemukan."));
        closeArisan(group);
    }

    private void closeArisan(ArisanGroupEntity group) {
        // Cek dulu apakah semua beneran sudah menang
        long remainingWinners = arisanMemberRepository.countByArisanGroupIdAndHasWon(group.getId(), false);
        if (remainingWinners > 0) {
            throw new ArisanException("Tidak bisa menutup arisan, masih ada " + remainingWinners + " anggota yang belum menang.");
        }
        
        group.setStatus(ArisanStatus.FINISHED);
        arisanGroupRepository.save(group);
    }
}
