package com.example.eeza.backendbuhoseat.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "reviews")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "stars")
    private Integer stars;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "idLocal", foreignKey = @ForeignKey(name = "fk_local"))
    private Local local;
}
