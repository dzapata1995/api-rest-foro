package com.dzapata.foro.repositories;

import com.dzapata.foro.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {
    Optional<CursoEntity> findByCodigo(String codigo);
}
