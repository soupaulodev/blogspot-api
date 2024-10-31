package br.com.soupaulodev.blogspot.modules.validation;

import br.com.soupaulodev.blogspot.modules.post.controller.dto.UpdatePostRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneFieldValidator implements ConstraintValidator<AtLeastOneField, UpdatePostRequestDTO> {

    @Override
    public boolean isValid(UpdatePostRequestDTO dto, ConstraintValidatorContext context) {
        return (dto.title() != null && !dto.title().isEmpty()) ||
                (dto.resume() != null && !dto.resume().isEmpty()) ||
                (dto.content() != null && !dto.content().isEmpty());
    }
}
