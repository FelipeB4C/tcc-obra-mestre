package com.tcc.reforma.reforma.domain.usuario;

import com.tcc.reforma.reforma.domain.enums.TipoUsuario;
import com.tcc.reforma.reforma.domain.enums.UserRole;
import com.tcc.reforma.reforma.validation.UsuarioInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@UsuarioInsert
public class UsuarioCreateDTO {

    @NotBlank
    private final String nome;

    @NotNull
    private final TipoUsuario tipoUsuario;

    @NotBlank
    private final String cpfOuCnpj;

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private String senha;

    @NotBlank
    private final String contato1;
    private final String contato2;

    @NotBlank
    private final String estado;
    @NotBlank
    private final String cidade;

    public UsuarioCreateDTO(String nome, Integer tipoUsuario, String cpfOuCnpj, String email, String senha,
                            String contato1, String contato2, String estado, String cidade) {
        this.nome = nome;
        this.tipoUsuario = TipoUsuario.toEnum(tipoUsuario);
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.estado = estado;
        this.cidade = cidade;
    }

    public Usuario toModel(UsuarioCreateDTO request) {
        return new Usuario(nome, tipoUsuario, cpfOuCnpj, email, senha, contato1, contato2, estado, cidade);
    }

}
