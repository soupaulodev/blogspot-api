package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.exceptions.PostNotFoundException;
import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UpdatePostUsecase {

    private final PostRepository postRepository;

    public UpdatePostUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity execute(UUID id, PostEntity postEntity) {
        PostEntity existingPost = postRepository.findById(id).orElseThrow(PostNotFoundException::new);

        if (postEntity.getTitle() != null && !postEntity.getTitle().isEmpty()) {
            existingPost.setTitle(postEntity.getTitle());
        }
        if (postEntity.getResume() != null && !postEntity.getResume().isEmpty()) {
            existingPost.setResume(postEntity.getResume());
        }
        if (postEntity.getContent() != null && !postEntity.getContent().isEmpty()) {
            existingPost.setContent(postEntity.getContent());
        }

        postEntity.setUpdatedAt(Instant.now());
        return postRepository.save(existingPost);
    }
}
