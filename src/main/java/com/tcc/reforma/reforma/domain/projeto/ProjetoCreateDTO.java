package com.tcc.reforma.reforma.domain.projeto;

import com.tcc.reforma.reforma.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public record ProjetoCreateDTO(@NotBlank String nomeProjeto, @NotBlank String descTrabalho, @NotBlank String estado, @NotBlank String cidade, @NotBlank String dataInicio) {


    public Projeto toModel(Usuario cliente){
        return new Projeto(cliente, nomeProjeto(), descTrabalho(), estado(), cidade(), toLocalDate(dataInicio));
    }

    public LocalDate toLocalDate(String data){
        DateTimeFormatter frmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.from(frmt.parse(data));
    }
}
