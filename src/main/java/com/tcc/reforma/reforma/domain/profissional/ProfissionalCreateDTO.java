package com.tcc.reforma.reforma.domain.profissional;

import com.tcc.reforma.reforma.domain.enums.Ocupacao;
import com.tcc.reforma.reforma.domain.profissao.Profissao;
import com.tcc.reforma.reforma.domain.profissao.ProfissaoCreateDTO;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalCreateDTO {

    @NotNull
    private List<ProfissaoCreateDTO> listProfissao = new ArrayList<>();

    @NotBlank
    private final String descricao;
    @NotEmpty
    private final List<String> localAtendimento;

    @NotNull
    private final Boolean atendimentoOnline;

    public ProfissionalCreateDTO(List<ProfissaoCreateDTO> listProfissao, String descricao,
                                 List<String> localAtendimento, Boolean atendimentoOnline) {
        this.listProfissao = listProfissao;
        this.descricao = descricao;
        this.localAtendimento = localAtendimento;
        this.atendimentoOnline = atendimentoOnline;
    }

    public Profissional toModel(Usuario usuario) {
        Profissional profissional = new Profissional(descricao, localAtendimento, atendimentoOnline, usuario);
        List<Profissao> profissaoStream = listProfissao.stream().map(p -> toList(p, profissional)).toList();
        profissaoStream.forEach(p -> profissional.getListaProfissao().add(p));
        return profissional;
    }

    public Profissao toList(ProfissaoCreateDTO dto, Profissional pro) {
        return new Profissao(Ocupacao.toEnum(dto.getOcupacao()), dto.getCodProfissao(), pro);
    }
}
