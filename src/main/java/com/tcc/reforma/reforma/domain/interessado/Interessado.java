package com.tcc.reforma.reforma.domain.interessado;


import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Interessado {

    @EmbeddedId
    private InteressadoPK id = new InteressadoPK();

    public Interessado(Projeto projeto, Profissional profissional){
        id.setProjeto(projeto);
        id.setProfissional(profissional);
    }

}
