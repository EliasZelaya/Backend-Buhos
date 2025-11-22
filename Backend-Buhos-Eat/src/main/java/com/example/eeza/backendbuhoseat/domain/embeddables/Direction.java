package com.example.eeza.backendbuhoseat.domain.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direction {
    @Column(name = "latitude")
    private Double lat;
    @Column(name = "longitude")
    private Double lng;
}
