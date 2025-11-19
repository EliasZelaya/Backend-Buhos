package com.example.eeza.backendbuhoseat.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "plate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plate {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "idLocal", foreignKey = @ForeignKey(name = "fk_local"))
    private Local local;
}
