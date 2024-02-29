package com.tcc.reforma.reforma.domain.enums;

public enum TipoUsuario {

    PESSOA_FISICA(0, "Pessoa Física"),
    PESSOA_JURIDICA(1, "Pessoa Jurídica");

    private final int cod;
    private final String descricao;

    TipoUsuario(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String descricao() {
        return descricao;
    }

    public static TipoUsuario toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoUsuario x : TipoUsuario.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
