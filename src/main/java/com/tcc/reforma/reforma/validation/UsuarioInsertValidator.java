package com.tcc.reforma.reforma.validation;

import com.tcc.reforma.reforma.exception.FieldMessage;
import com.tcc.reforma.reforma.domain.enums.TipoUsuario;
import com.tcc.reforma.reforma.domain.usuario.Usuario;
import com.tcc.reforma.reforma.domain.usuario.UsuarioCreateDTO;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioCreateDTO> {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void initialize(UsuarioInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UsuarioCreateDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipoUsuario().equals(TipoUsuario.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipoUsuario().equals(TipoUsuario.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        UserDetails aux = repository.findByEmail(objDto.getEmail());
            if (aux != null) {
                list.add(new FieldMessage("email", "Email já existe"));
            }

            for (FieldMessage e : list) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                        .addPropertyNode(e.getFieldName()).addConstraintViolation();
            }
            return list.isEmpty();
        }
}
