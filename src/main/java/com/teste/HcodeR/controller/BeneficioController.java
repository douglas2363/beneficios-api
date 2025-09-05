package com.teste.HcodeR.controller;

import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.service.BeneficioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Benefícios", description = "CRUD de benefícios")
@RestController
@RequestMapping("/api/beneficios")
@RequiredArgsConstructor
public class BeneficioController {

    private final BeneficioService service;

    @Operation(summary = "Lista benefícios")
    @GetMapping
    public List<Beneficio> listar() {
        return service.listar();
    }

    @Operation(summary = "Salvar um benefício")
    @PostMapping
    public Beneficio salvar(@RequestBody Beneficio beneficio) {
        return service.salvar(beneficio);
    }

    @Operation(summary = "Deletar  um benefício")
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
    @Operation(summary = "Desativar ou Ativarum benefício")
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Beneficio> deactivateBenefit(@PathVariable Long id) {
        Beneficio updated = service.desativarBeneficio(id);
        return ResponseEntity.ok(updated);
    }


}
