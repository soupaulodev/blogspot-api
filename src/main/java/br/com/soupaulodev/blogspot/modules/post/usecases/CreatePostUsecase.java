package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePostUsecase {

    private final PostRepository postRepository;

    public CreatePostUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity execute(PostEntity postEntity) {
        return postRepository.save(postEntity);
    }
}
