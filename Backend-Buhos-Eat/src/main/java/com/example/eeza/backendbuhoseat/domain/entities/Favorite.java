package com.example.eeza.backendbuhoseat.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorites")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    @EmbeddedId
    private UserXLocalId id = new UserXLocalId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user"))
    private User user;

    @ManyToOne
    @MapsId("localId")
    @JoinColumn(name = "local_id", foreignKey = @ForeignKey(name = "fk_local"))
    private Local local;
}
