package com.tcc.reforma.reforma.domain.profissao;

import com.tcc.reforma.reforma.domain.enums.Ocupacao;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProfissaoCreateDTO {

    @NotNull
    private Integer ocupacao;
    private String codProfissao;

    public ProfissaoCreateDTO(Integer ocupacao, String codProfissao){
        this.ocupacao = ocupacao;
        this.codProfissao = verifyCodProfissao(codProfissao, ocupacao);
    }

    private String verifyCodProfissao(String codigo, int ocupacao) {
        if(ocupacao != Ocupacao.ENGENHEIRO.getId() || ocupacao != Ocupacao.ARQUITETO.getId()){
            return "Não aplicado";
        }
        return codigo;
    }

}
