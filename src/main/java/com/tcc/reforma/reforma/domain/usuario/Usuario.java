package com.tcc.reforma.reforma.domain.usuario;

import com.tcc.reforma.reforma.domain.enums.TipoUsuario;
import com.tcc.reforma.reforma.domain.enums.UserRole;
import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;
    private String cpfOuCnpj;
    @Column(unique = true)
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String contato1;
    private String contato2;
    private String estado;
    private String cidade;
    private Integer numAvaliacoes = 0;
    private Double notaCliente = 0.0;
    private String imgUrl;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Projeto> projetosCriados = new ArrayList<>();


    public Usuario(String nome, TipoUsuario tipoUsuario, String cpfOuCnpj, String email, String senha,
                   String contato1, String contato2, String estado, String cidade) {
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.senha = senha;
        this.role = UserRole.USER;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.estado = estado;
        this.cidade = cidade;
    }

    public void setProjetosCriados(Projeto projeto) {
        this.projetosCriados.add(projeto);
    }

    public void setNullProjetosCriados() {
        this.projetosCriados.clear();
    }

}
