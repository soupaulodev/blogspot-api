package br.com.soupaulodev.blogspot.modules.post.controller.dto;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record CreatePostRequestDTO(
    @Length(min = 3, max = 50)
    String title,
    @Length(min = 3, max = 150)
    String resume,
    @NotBlank
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
