package com.teste.HcodeR.domain.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BenefitEvent {
    private Long id;
    private String type;
    private String name;
    private Boolean active;
    private Instant occurredAt;

}
