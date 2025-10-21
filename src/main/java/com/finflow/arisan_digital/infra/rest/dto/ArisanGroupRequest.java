package com.finflow.arisan_digital.infra.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ArisanGroupRequest {
   @NotBlank(message = "Nama grup tidak boleh kosong")
    @Size(min = 3, max = 100, message = "Nama grup harus antara 3 dan 100 karakter")
    private String name;

    @NotNull(message = "Jumlah pembayaran tidak boleh kosong")
    @Positive(message = "Jumlah pembayaran harus lebih besar dari 0")
    private BigDecimal paymentAmount;

    @NotNull(message = "Jumlah slot tidak boleh kosong")
    @Min(value = 2, message = "Minimal harus ada 2 slot/anggota")
    private Integer totalSlots;

}
