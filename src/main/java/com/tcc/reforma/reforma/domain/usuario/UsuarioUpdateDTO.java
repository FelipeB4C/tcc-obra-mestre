package com.tcc.reforma.reforma.domain.usuario;

import com.tcc.reforma.reforma.domain.enums.TipoUsuario;
import com.tcc.reforma.reforma.validation.UsuarioUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@UsuarioUpdate
public class UsuarioUpdateDTO {

    @NotBlank
    private String nome;
    @NotNull
    private TipoUsuario tipoUsuario;
    @NotBlank
    private String cpfOuCnpj;
    @NotBlank
    private String contato1;
    private String contato2;
    @NotBlank
    private String estado;
    @NotBlank
    private String cidade;

    public UsuarioUpdateDTO(String nome, Integer tipoUsuario, String cpfOuCnpj, String contato1,
                            String contato2, String estado, String cidade) {
        this.nome = nome;
        this.tipoUsuario = TipoUsuario.toEnum(tipoUsuario);
        this.cpfOuCnpj = cpfOuCnpj;
        this.contato1 = contato1;
        this.contato2 = contato2;
        this.estado = estado;
        this.cidade = cidade;
    }

    public void updateData(Usuario newUsuario){
        newUsuario.setNome(this.getNome());
        newUsuario.setTipoUsuario(this.getTipoUsuario());
        newUsuario.setCpfOuCnpj(this.getCpfOuCnpj());
        newUsuario.setContato1(this.getContato1());
        newUsuario.setContato2(this.getContato2());
        newUsuario.setEstado(this.getEstado());
        newUsuario.setCidade(this.getCidade());
    }

}
