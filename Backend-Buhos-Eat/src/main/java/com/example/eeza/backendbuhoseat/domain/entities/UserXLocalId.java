package com.example.eeza.backendbuhoseat.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserXLocalId {
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "local_id")
    private UUID localId;
}
