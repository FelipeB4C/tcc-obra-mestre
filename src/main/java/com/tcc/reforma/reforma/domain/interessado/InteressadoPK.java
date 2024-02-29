package com.tcc.reforma.reforma.domain.interessado;

import com.tcc.reforma.reforma.domain.profissional.Profissional;
import com.tcc.reforma.reforma.domain.projeto.Projeto;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class InteressadoPK {

    @ManyToOne
    @JoinColumn(name = "")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "")
    private Profissional profissional;



}
