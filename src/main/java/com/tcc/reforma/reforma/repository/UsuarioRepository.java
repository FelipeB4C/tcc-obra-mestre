package com.tcc.reforma.reforma.repository;

import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("from Usuario u where u.profissional is not null")
    List<Usuario> findAllProfessionals();

    @Query("from Usuario u where u.profissional = ?1")
    Usuario findByIdProfissional(Profissional profissional);

    //Usuario findByEmail(String email);

    UserDetails findByEmail(String email);
}
