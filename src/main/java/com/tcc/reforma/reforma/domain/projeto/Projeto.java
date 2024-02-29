package com.tcc.reforma.reforma.domain.projeto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcc.reforma.reforma.domain.interessado.Interessado;
import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Usuario cliente;

    @ManyToOne
    @JoinColumn(name="id_profissional")
    private Profissional profissional;

    private String nomeProjeto;

    private String descTrabalho;

    private String estado;
    private String cidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    @OneToMany(mappedBy = "id.projeto")
    private List<Interessado> interessados = new ArrayList<>();

    private boolean concluido;

    public Projeto(Usuario cliente, String nomeProjeto, String descTrabalho, String estado, String cidade, LocalDate dataInicio) {
        this.nomeProjeto = nomeProjeto;
        this.cliente = cliente;
        this.descTrabalho = descTrabalho;
        this.estado = estado;
        this.cidade = cidade;
        this.dataInicio = dataInicio;
        this.concluido = false;
    }

    /* public void setNullInteressados() {
        this.interessados.clear();
    }*/

}
