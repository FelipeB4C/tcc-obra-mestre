package com.tcc.reforma.reforma.repository;

import com.tcc.reforma.reforma.domain.profissional.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {

}
