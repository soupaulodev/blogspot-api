package br.com.soupaulodev.blogspot.modules.post.controller.dto;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PostResponseDTO(
    UUID id,
    String title,
    String resume,
    String content,
    String author
) {

    public static PostResponseDTO fromEntity(PostEntity entity) {
        return new PostResponseDTO(
            entity.getId(),
            entity.getTitle(),
            entity.getResume(),
            entity.getContent(),
            entity.getAuthor()
        );
    }
}
