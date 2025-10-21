package com.finflow.arisan_digital.infra.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finflow.arisan_digital.domain.usecase.CreateArisanGroupUseCase;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupRequest;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

// Annotation khusus untuk ngetes lapisan Controller
@WebMvcTest(ArisanController.class) 
class ArisanControllerTest {

    @Autowired
    private MockMvc mockMvc; // Untuk simulasi HTTP Request

    @Autowired
    private ObjectMapper objectMapper; // Untuk konversi Java Object ke JSON

    // Mock Use Case (kita tidak mau Use Case benar-benar jalan)
    @MockBean
    private CreateArisanGroupUseCase createArisanGroupUseCase;

    @Test
    void testCreateGroup_Success() throws Exception {
        // Arrange
        ArisanGroupRequest request = new ArisanGroupRequest();
        request.setName("Arisan Bulanan");
        request.setPaymentAmount(BigDecimal.valueOf(50000));
        request.setTotalSlots(5);
        
        ArisanGroupResponse mockResponse = ArisanGroupResponse.builder()
                .id(1L).name("Arisan Bulanan").totalSlots(5).build();

        // Mocking: Kapanpun useCase.execute dipanggil, kembalikan mockResponse
        when(createArisanGroupUseCase.execute(any(ArisanGroupRequest.class)))
                .thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(post("/api/v1/arisan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))) // Ubah DTO jadi JSON
                
                // Verifikasi HTTP Status 201 Created
                .andExpect(status().isCreated()) 
                // Verifikasi content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
                // Verifikasi field di response body
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Arisan Bulanan")));
    }

    @Test
    void testCreateGroup_ValidationFails() throws Exception {
        // Arrange (Input Invalid: Nama kosong, Payment 0)
        ArisanGroupRequest invalidRequest = new ArisanGroupRequest();
        invalidRequest.setName(""); 
        invalidRequest.setPaymentAmount(BigDecimal.ZERO);
        invalidRequest.setTotalSlots(10);
        
        // Act & Assert
        mockMvc.perform(post("/api/v1/arisan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                
                // Verifikasi HTTP Status 400 Bad Request (Karena Validasi Gagal)
                .andExpect(status().isBadRequest())
                // Verifikasi pesan error dari GlobalExceptionHandler kita
                .andExpect(jsonPath("$.message", is("Validasi gagal")))
                .andExpect(jsonPath("$.errors.name").exists())
                .andExpect(jsonPath("$.errors.paymentAmount").exists());
    }
}