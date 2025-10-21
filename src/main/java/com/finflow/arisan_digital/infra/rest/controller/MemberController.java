package com.finflow.arisan_digital.infra.rest.controller;


import com.finflow.arisan_digital.domain.usecase.InviteMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    
    private final InviteMemberUseCase inviteMemberUseCase;

    // undang ges
    @PostMapping("/invite")
    public ResponseEntity<Void> inviteMember(
            @RequestParam Long groupId, 
            @RequestParam String email) {
        
        inviteMemberUseCase.execute(groupId, email);
        return ResponseEntity.ok().build();
    }

    // TODO: Tambahkan endpoint untuk payment besok kali 
    // @PostMapping("/{memberId}/payments")
    // ...
}
