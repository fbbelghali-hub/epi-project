package com.example.epi.controller;

import com.example.epi.service.ImportUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportUserService importUserService;

    @PostMapping("/users")
    public String importUsers() {

        importUserService.importUsers();

        return "Import termine avec succes";
    }
}
