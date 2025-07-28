package com.teste.HcodeR.service;

import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.repository.BeneficioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository repository;

    public List<Beneficio> listar() {
        return repository.findAll();
    }

    public Beneficio salvar(Beneficio beneficio) {
        return repository.save(beneficio);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Beneficio desativarBeneficio(Long id) {
        Beneficio benefit = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefit not found with id: " + id));

        benefit.setActive(false);
        return repository.save(benefit);
    }
}
