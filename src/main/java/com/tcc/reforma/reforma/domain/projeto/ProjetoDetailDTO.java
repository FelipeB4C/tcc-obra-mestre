package com.tcc.reforma.reforma.domain.projeto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tcc.reforma.reforma.domain.profissional.ProfissionalDetailDTO;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.domain.usuario.UsuarioDetailDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ProjetoDetailDTO {

    private Long id;
    private UsuarioDetailDTO cliente;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProfissionalDetailDTO profissional;
    private String nomeProjeto;
    private String descTrabalho;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    private String estado;
    private String cidade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<ProfissionalDetailDTO> profissionaisInteressados = new ArrayList<>();

    private boolean concluido;

    public ProjetoDetailDTO(Projeto projeto) {
        this.id = projeto.getId();
        this.cliente = new UsuarioDetailDTO(projeto.getCliente());
        this.nomeProjeto = projeto.getNomeProjeto();
        this.descTrabalho = projeto.getDescTrabalho();
        this.dataInicio = projeto.getDataInicio();
        this.estado = projeto.getEstado();
        this.cidade = projeto.getCidade();
        this.concluido = projeto.isConcluido();
    }

    public ProjetoDetailDTO(Projeto projeto, Usuario profissional) {
        this.id = projeto.getId();
        this.cliente = verificaCliente(projeto.getCliente());
        this.profissional = verificaProfissional(profissional);
        this.nomeProjeto = projeto.getNomeProjeto();
        this.descTrabalho = projeto.getDescTrabalho();
        this.dataInicio = projeto.getDataInicio();
        this.estado = projeto.getEstado();
        this.cidade = projeto.getCidade();
        this.concluido = projeto.isConcluido();
    }

    public ProfissionalDetailDTO verificaProfissional(Usuario profissional){
        if(profissional != null){
            return new ProfissionalDetailDTO(profissional);
        }
        return null;
    }

    public UsuarioDetailDTO verificaCliente(Usuario cliente){
        if(cliente != null){
            return new UsuarioDetailDTO(cliente);
        }
        return null;
    }

    public void setProfissionaisInteressados(List<ProfissionalDetailDTO> profissionaisInteressados) {
        this.profissionaisInteressados = profissionaisInteressados;
    }
}
