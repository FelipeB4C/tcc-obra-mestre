package com.tcc.reforma.reforma.repository;

import com.tcc.reforma.reforma.domain.profissao.Profissao;
import com.tcc.reforma.reforma.domain.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfissaoRepository extends JpaRepository<Profissao, Long> {

   // List<Profissao> findAllByProfissional(Profissional profissional);

}
