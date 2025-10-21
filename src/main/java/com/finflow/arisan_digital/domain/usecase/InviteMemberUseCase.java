package com.finflow.arisan_digital.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InviteMemberUseCase {
    
    public void execute(Long groupId, String userEmail) {
        // TODO: Implementasi logika kirim email/notifikasi
        // Misal: Panggil service EmailSender
        System.out.println("Mengirim undangan ke " + userEmail + " untuk grup " + groupId);
    }
}
