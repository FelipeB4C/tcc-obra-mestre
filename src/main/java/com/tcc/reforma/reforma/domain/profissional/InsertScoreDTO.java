package com.tcc.reforma.reforma.domain.profissional;

import com.tcc.reforma.reforma.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class InsertScoreDTO {

    @NotNull
    private Double score;

    public void updateDateProfissional(Profissional profissional) {
        profissional.setNotaProfissional(calcScoreProfissional(profissional));
        profissional.setNumAvaliacoes(profissional.getNumAvaliacoes() + 1);
        profissional.setNumProjetosFeitos(profissional.getNumProjetosFeitos() + 1);
    }

    public Double calcScoreProfissional(Profissional profissional){
        var numAvaliacoes = profissional.getNumAvaliacoes();
        var notaTotal = (numAvaliacoes * profissional.getNotaProfissional()) + score;
        var media = notaTotal / (numAvaliacoes + 1);
        return media;
    }

    public void updateDateCliente(Usuario cliente) {
        cliente.setNotaCliente(calcScoreCliente(cliente));
        cliente.setNumAvaliacoes(cliente.getNumAvaliacoes() + 1);
    }

    public Double calcScoreCliente(Usuario cliente){
        var numAvaliacoes = cliente.getNumAvaliacoes();
        var notaTotal = (numAvaliacoes * cliente.getNotaCliente()) + score;
        var media = notaTotal / (numAvaliacoes + 1);
        return media;
    }

    public Double getScore() {
        return score;
    }
}
