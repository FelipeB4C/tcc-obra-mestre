package com.tcc.reforma.reforma.repository;

import com.tcc.reforma.reforma.domain.interessado.Interessado;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteressadoRepository extends JpaRepository<Interessado, Long> {

    @Query("from Interessado i where i.id.projeto = ?1")
    List<Interessado> findByIdProjeto(Projeto projeto);

}
