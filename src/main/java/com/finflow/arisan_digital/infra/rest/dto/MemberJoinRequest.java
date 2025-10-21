package com.finflow.arisan_digital.infra.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberJoinRequest {

    @NotBlank(message = "User ID tidak boleh kosong")
    private String userId;

    @NotBlank(message = "Nama member tidak boleh kosong")
    private String name;
}
