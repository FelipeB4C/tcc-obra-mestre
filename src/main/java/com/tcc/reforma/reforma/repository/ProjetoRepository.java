package com.tcc.reforma.reforma.repository;

import com.tcc.reforma.reforma.domain.projeto.Projeto;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("from Projeto p where p.profissional is null")
    List<Projeto> projetosDisponiveis();

    @Query("from Projeto p where p.profissional = ?1")
    List<Projeto> projetosPorProfissional(Usuario usuario);

}
