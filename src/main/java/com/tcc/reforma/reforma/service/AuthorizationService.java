package com.tcc.reforma.reforma.service;

import com.tcc.reforma.reforma.domain.usuario.UserLogin;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = repository.findByEmail(email);
        if(usuario == null) {
            throw new UsernameNotFoundException(email);
        }
       return new UserLogin(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getRole());
    }
}
