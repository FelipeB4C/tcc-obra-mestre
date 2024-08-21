package com.tcc.reforma.reforma.domain.usuario;

import lombok.Getter;

@Getter
public class UsuarioDetailDTO {

    private Long id;
    private Long idProfissional;
    private String nome;
    private String tipoUsuario;
    private String cpfOuCnpj;
    private String email;
    private String contato1;
    private String contato2;
    private String estado;
    private String cidade;
    private Double notaCliente;
    private Integer numAvaliacoes;


    public UsuarioDetailDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.idProfissional = (usuario.getProfissional() != null) ? usuario.getProfissional().getId() : null;
        this.nome = usuario.getNome();
        this.tipoUsuario = usuario.getTipoUsuario().descricao();
        this.cpfOuCnpj = usuario.getCpfOuCnpj();
        this.email = usuario.getEmail();
        this.contato1 = usuario.getContato1();
        this.contato2 = usuario.getContato2();
        this.estado = usuario.getEstado();
        this.cidade = usuario.getCidade();
        this.notaCliente = usuario.getNotaCliente();
        this.numAvaliacoes = usuario.getNumAvaliacoes();
    }
}
