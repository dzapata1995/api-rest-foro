package com.dzapata.foro.repositories;

import com.dzapata.foro.entities.RespuestaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<RespuestaEntity, Long> {
}
