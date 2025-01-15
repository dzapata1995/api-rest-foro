package com.dzapata.foro.repositories;

import com.dzapata.foro.entities.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<TopicoEntity, Long>, JpaSpecificationExecutor<TopicoEntity> {

}
