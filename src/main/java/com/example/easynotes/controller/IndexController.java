package com.example.easynotes.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Landing Controller
 */

@RestController
@RequestMapping("/api")
public class IndexController {
    @GetMapping("/index")
    @Operation(summary = "index 조회")
    public String index() {
        return "Landing Index!";
    }
}
