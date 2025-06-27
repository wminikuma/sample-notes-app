package com.example.easynotes.controller;

import com.example.easynotes.dto.IndexApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Landing Controller
 */

@RestController
@RequestMapping("/api")
@Tag(name = "Index", description = "API Index Test")
public class IndexController {
    @GetMapping("/index")
    @Operation(summary = "index 조회", description = "API 초기 연동 상태를 점검합니다.")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    public ResponseEntity<IndexApiResponse> index() {
        IndexApiResponse response = IndexApiResponse.of(200, "Landing Index!");
        return ResponseEntity.ok(response);
    }
}
