package com.tcc.reforma.reforma.domain.projeto;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record ProjetoUpdateDTO(@NotBlank String nomeProjeto, @NotBlank String descTrabalho, @NotBlank String dataInicio, @NotBlank String estado, @NotBlank String cidade) {

    public void updateData(Projeto projeto){
        projeto.setNomeProjeto(nomeProjeto());
        projeto.setDescTrabalho(descTrabalho());
        projeto.setDataInicio(toLocalDate(dataInicio()));
        projeto.setEstado(estado());
        projeto.setCidade(cidade());
    }

    public LocalDate toLocalDate(String data){
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(frmt.parse(data));
    }

}
