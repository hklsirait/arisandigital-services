package com.finflow.arisan_digital.domain.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutoPaymentUseCase {
    
    // Tambahkan batch scheduler yang jalan tiap jam 5 pagi
    // (Tambahkan @EnableScheduling di ArisanDigitalApplication.java)
    @Scheduled(cron = "0 0 5 * * ?")
    public void executeScheduledPayments() {
        // TODO:
        // 1. Cari semua grup arisan yang ACTIVE
        // 2. Untuk tiap grup, cari semua member
        // 3. Cek apakah member sudah bayar untuk periode ini
        // 4. Jika belum, coba debet via payment gateway / virtual account
        // 5. Buat record PaymentEntity
        // 6. Kirim notifikasi ke user via email / push notification kalii
        System.out.println("Menjalankan proses pembayaran otomatis terjadwal...");
    }
}
