package com.tcc.reforma.reforma.domain.profissao;

import com.tcc.reforma.reforma.domain.enums.Ocupacao;
import lombok.Setter;


public class ProfissaoUpdateDTO {

    private String codProfissao;
    private Integer ocupacao;


    public ProfissaoUpdateDTO(String codProfissao, Integer ocupacao) {
        System.out.println("CodProfissao: "+codProfissao);
        this.codProfissao = codProfissao;
        this.ocupacao = ocupacao;
    }

    public void updateData(Profissao newProfissao){
        newProfissao.setCodProfissao(this.codProfissao);
        newProfissao.setOcupacao(Ocupacao.toEnum(this.ocupacao));
    }

}
