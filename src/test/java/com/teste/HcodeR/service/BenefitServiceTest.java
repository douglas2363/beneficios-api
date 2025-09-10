package com.teste.HcodeR.service;

import com.teste.HcodeR.domain.Beneficio;
import com.teste.HcodeR.repository.BeneficioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BenefitServiceTest {
    @Mock
    private BeneficioRepository repository;

    @InjectMocks
    private BeneficioService service;

    @Test
    void shouldDeactivateBenefitSuccessfully_whenBenefitExists() {
        // given
        Long id = 1L;
        Beneficio benefit = new Beneficio();
        benefit.setId(id);
        benefit.setNome("Vale Alimentação");
        benefit.setDescricao("Cartão alimentação mensal");
        benefit.setActive(true);

        when(repository.findById(id)).thenReturn(Optional.of(benefit));
        when(repository.save(any(Beneficio.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Beneficio result = service.desativar(id);

        // then
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertFalse(result.isActive());

        verify(repository).findById(id);
        verify(repository).save(benefit);
    }
}
