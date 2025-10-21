package com.finflow.arisan_digital.infra.rest.controller;

import com.finflow.arisan_digital.domain.usecase.CloseArisanUseCase;
import com.finflow.arisan_digital.domain.usecase.CreateArisanGroupUseCase;
import com.finflow.arisan_digital.domain.usecase.DrawWinnerUseCase;
import com.finflow.arisan_digital.domain.usecase.JoinArisanUseCase;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupRequest;
import com.finflow.arisan_digital.infra.rest.dto.ArisanGroupResponse;
import com.finflow.arisan_digital.infra.rest.dto.DrawResultResponse;
import com.finflow.arisan_digital.infra.rest.dto.MemberJoinRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/arisan")
@RequiredArgsConstructor
public class ArisanController {
   private final CreateArisanGroupUseCase createArisanGroupUseCase;
    private final JoinArisanUseCase joinArisanUseCase;
    private final DrawWinnerUseCase drawWinnerUseCase;
    private final CloseArisanUseCase closeArisanUseCase;

    @PostMapping
    public ResponseEntity<ArisanGroupResponse> createGroup(
            @Valid @RequestBody ArisanGroupRequest request) {
        
        ArisanGroupResponse response = createArisanGroupUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{groupId}/join")
    public ResponseEntity<Void> joinGroup(
            @PathVariable Long groupId,
            @Valid @RequestBody MemberJoinRequest request) {
        
        joinArisanUseCase.execute(groupId, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{groupId}/draw")
    public ResponseEntity<DrawResultResponse> drawWinner(@PathVariable Long groupId) {
        DrawResultResponse response = drawWinnerUseCase.execute(groupId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{groupId}/close")
    public ResponseEntity<Void> closeGroup(@PathVariable Long groupId) {
        closeArisanUseCase.execute(groupId);
        return ResponseEntity.ok().build();
    }
}
