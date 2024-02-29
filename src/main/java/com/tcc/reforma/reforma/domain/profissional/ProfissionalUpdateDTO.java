package com.tcc.reforma.reforma.domain.profissional;

import com.tcc.reforma.reforma.domain.profissao.Profissao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

public record ProfissionalUpdateDTO(@NotBlank String descricao, @NotEmpty List<String> localAtendimento,
                                    @NotNull Boolean atendimentoOnline) {


    public void updateData(Profissional newPedreiro) {
        newPedreiro.setDescricao(descricao());
        newPedreiro.setLocalAtendimento(localAtendimento());
        newPedreiro.setAtendimentoOnline(atendimentoOnline());
    }


}
