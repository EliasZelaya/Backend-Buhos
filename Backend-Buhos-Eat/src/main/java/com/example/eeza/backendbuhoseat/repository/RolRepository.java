package com.example.eeza.backendbuhoseat.repository;

import com.example.eeza.backendbuhoseat.domain.dto.response.rol.RolResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolRepository extends JpaRepository<Rol, UUID> {
}
