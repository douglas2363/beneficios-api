package com.teste.HcodeR.controller;

import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.service.BeneficioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficios")
@RequiredArgsConstructor
public class BeneficioController {

    private final BeneficioService service;

    @GetMapping
    public List<Beneficio> listar() {
        return service.listar();
    }

    @PostMapping
    public Beneficio salvar(@RequestBody Beneficio beneficio) {
        return service.salvar(beneficio);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Beneficio> deactivateBenefit(@PathVariable Long id) {
        Beneficio updated = service.desativarBeneficio(id);
        return ResponseEntity.ok(updated);
    }


}
