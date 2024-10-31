package br.com.soupaulodev.blogspot.modules.post.controller.dto;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.validation.AtLeastOneField;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
@AtLeastOneField
public record UpdatePostRequestDTO(
    @Length(min = 3, max = 50)
    String title,
    @Length(min = 3, max = 150)
    String resume,
    @Length(min = 3)
    String content
) {
    public PostEntity toEntity(String author) {
        return PostEntity.builder()
            .title(title)
            .resume(resume)
            .content(content)
            .build();
    }
}
