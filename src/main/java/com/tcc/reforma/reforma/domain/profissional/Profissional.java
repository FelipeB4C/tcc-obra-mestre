package com.tcc.reforma.reforma.domain.profissional;

import com.tcc.reforma.reforma.domain.interessado.Interessado;
import com.tcc.reforma.reforma.domain.profissao.Profissao;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<Profissao> listaProfissao = new ArrayList<>();

    private String descricao;

    private List<String> localAtendimento;

    private Boolean atendimentoOnline;

    private Integer numProjetosFeitos = 0;

    private Integer numAvaliacoes = 0;

    private Double notaProfissional = 0.0;

    @OneToOne(mappedBy = "profissional")
    private Usuario usuario;

    @OneToMany(mappedBy = "id.profissional")
    private List<Interessado> projetosInteressados = new ArrayList<>();

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL)
    private List<Projeto> projetosTrabalhados = new ArrayList<>();

    public Profissional(String descricao, List<String> localAtendimento, Boolean atendimentoOnline, Usuario usuario) {
        this.descricao = descricao;
        this.localAtendimento = localAtendimento;
        this.atendimentoOnline = atendimentoOnline;
        this.usuario = usuario;
    }

    public void setProjetosTrabalhados(Projeto projeto) {
        this.projetosTrabalhados.add(projeto);
    }

    public void setNullProjetosTrabalhados() {
        this.projetosTrabalhados.clear();
    }

}
