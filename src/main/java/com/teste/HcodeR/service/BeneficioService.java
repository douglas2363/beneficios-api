package com.teste.HcodeR.service;

import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.messaging.BenefitEventPublisher;
import com.teste.HcodeR.repository.BeneficioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BeneficioService {

    private final BeneficioRepository repository;
    private final BenefitEventPublisher publisher;

    public List<Beneficio> listar() {
        return repository.findAll();
    }

    public Beneficio salvar(Beneficio beneficio) {
        Beneficio saved = repository.save(beneficio);
        publisher.publish("CREATED", saved);   // ✅ type vai no evento
        return saved;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
       // publicar evento só com ID
        Beneficio temp = new Beneficio();
        temp.setId(id);
        temp.setActive(false);
        temp.setNome("DELETED");
        publisher.publish("DELETED", temp);
    }


    public Beneficio desativar(Long id) {
        Beneficio b = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Benefício não encontrado: " + id));
        b.setActive(false);
        Beneficio updated = repository.save(b);
        publisher.publish("DEACTIVATED", updated);
        return updated;
    }
}
