package com.tcc.reforma.reforma.domain.profissao;

import com.tcc.reforma.reforma.domain.enums.Ocupacao;
import com.tcc.reforma.reforma.domain.profissional.Profissional;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Ocupacao ocupacao;
    private String codProfissao;

    @ManyToOne
    @JoinColumn(name = "id_profissional")
    private Profissional profissional;


    public Profissao(Ocupacao ocupacao, String codProfissao, Profissional profissional) {
        this.ocupacao = ocupacao;
        this.codProfissao = codProfissao;
        this.profissional = profissional;
    }

}
