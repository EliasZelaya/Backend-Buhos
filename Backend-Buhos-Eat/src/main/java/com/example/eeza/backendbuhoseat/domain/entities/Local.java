package com.example.eeza.backendbuhoseat.domain.entities;

import com.example.eeza.backendbuhoseat.utils.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "locals")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "phone")
    private String phone;

    @Column(name = "direction")
    private String direction;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "image")
    private String image;

    @Column(name = "schedule")
    private String schedule;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
