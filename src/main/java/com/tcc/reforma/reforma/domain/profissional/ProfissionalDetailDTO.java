package com.tcc.reforma.reforma.domain.profissional;

import com.tcc.reforma.reforma.domain.profissao.Profissao;
import com.tcc.reforma.reforma.domain.profissao.ProfissaoDetailDTO;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfissionalDetailDTO {


    private Long idUsuario;
    private Long idProfissional;
    private String nome;
    private String tipoUsuario;
    private String cpfOuCnpj;
    private String email;
    private String contato1;
    private String contato2;
    private List<ProfissaoDetailDTO> listProfissao = new ArrayList<>();
    private String descricao;
    private List<String> localAtendimento;
    private Boolean atendimentoOnline;
    private Double avaliacaoProfissional;
    private Integer numAvaliacoes;
    private String estado;
    private String cidade;

    public ProfissionalDetailDTO(Usuario request) {

        Profissional profissional = request.getProfissional();

        this.idUsuario = request.getId();
        this.idProfissional = request.getId();
        this.nome = request.getNome();
        this.tipoUsuario = request.getTipoUsuario().descricao();
        this.cpfOuCnpj = request.getCpfOuCnpj();
        this.email = request.getEmail();
        this.contato1 = request.getContato1();
        this.contato2 = request.getContato2();
        profissional.getListaProfissao().forEach(p -> toProfissaoDTO(p));
        this.descricao = profissional.getDescricao();
        this.localAtendimento = profissional.getLocalAtendimento();
        this.atendimentoOnline = profissional.getAtendimentoOnline();
        this.avaliacaoProfissional = profissional.getNotaProfissional();
        this.numAvaliacoes = profissional.getNumAvaliacoes();
        this.estado = request.getEstado();
        this.cidade = request.getCidade();
    }

    public void toProfissaoDTO(Profissao profissao){
        ProfissaoDetailDTO proDto = new ProfissaoDetailDTO(
                profissao.getId(),
                profissao.getOcupacao().getDescricao(),
                profissao.getCodProfissao()
        );
        this.listProfissao.add(proDto);
    }
}
