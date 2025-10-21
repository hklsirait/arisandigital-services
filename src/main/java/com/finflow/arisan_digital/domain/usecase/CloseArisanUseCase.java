package com.finflow.arisan_digital.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CloseArisanUseCase {
    // Kita panggil use case lain
    private final DrawWinnerUseCase drawWinnerUseCase;

    @Transactional
    public void execute(Long groupId) {
        // Logika penutupan kita delegasikan ke DrawWinnerUseCase
        // untuk memastikan validasi penutupan (semua sudah menang)
        drawWinnerUseCase.closeArisan(groupId);
    }
}
