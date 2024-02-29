package com.tcc.reforma.reforma.domain.enums;

public enum Ocupacao {

    PEDREIRO(0, "Pedreiro"),
    ELETRICISTA(1, "Eletricista"),
    DSGN_INTERIORES(2, "Designer de Interiores"),
    ARQUITETO(3, "Arquiteto"),
    ENGENHEIRO(4, "ENGENHEIRO");

    private final Integer id;
    private final String descricao;

    Ocupacao(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getId() {
        return id;
    }

    public static Ocupacao toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Ocupacao x : Ocupacao.values()) {
            if (cod.equals(x.getId())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
