package com.tcc.reforma.reforma.domain.enums;

public enum StatusPerfil {
    VERIFICADO(0, "Verificado"),
    NAO_VERIFICADO(1, "Não Verificado");

    private final Integer id;
    private final String descricao;

    StatusPerfil(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getId() {
        return id;
    }
}
