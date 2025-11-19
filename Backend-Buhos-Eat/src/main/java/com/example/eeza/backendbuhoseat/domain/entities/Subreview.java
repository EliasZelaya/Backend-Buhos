package com.example.eeza.backendbuhoseat.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "subreview")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subreview {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "idReview", foreignKey = @ForeignKey(name = "fk_review"))
    private Review review;

    @ManyToOne
    @JoinColumn(name = "idUser", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;
}
