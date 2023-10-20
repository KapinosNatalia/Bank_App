package de.telran.bankapp.controller;

import de.telran.bankapp.dto.AgreementDto;
import de.telran.bankapp.service.interfaces.AgreementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agreements")
@RequiredArgsConstructor
@Tag(name = "Agreement controller API", description = "With this controller you can get a list of all agreements")
public class AgreementController {
    private final AgreementService agreementService;

    @GetMapping
    @Operation(summary = "Get list of all agreements")
    public ResponseEntity<List<AgreementDto>> getAllAgreements() {
        return ResponseEntity.ok().body(agreementService.getAllAgreements());
    }
}
