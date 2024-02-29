package com.tcc.reforma.reforma.validation;

import com.tcc.reforma.reforma.exception.FieldMessage;
import com.tcc.reforma.reforma.domain.enums.TipoUsuario;
import com.tcc.reforma.reforma.domain.usuario.UsuarioUpdateDTO;
import com.tcc.reforma.reforma.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UsuarioUpdateValidator implements ConstraintValidator<UsuarioUpdate, UsuarioUpdateDTO> {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public void initialize(UsuarioUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UsuarioUpdateDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getTipoUsuario().equals(TipoUsuario.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDto.getTipoUsuario().equals(TipoUsuario.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

            for (FieldMessage e : list) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(e.getMessage())
                        .addPropertyNode(e.getFieldName()).addConstraintViolation();
            }
            return list.isEmpty();
        }
}
