package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.exceptions.PostNotFoundException;
import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPostUsecase {

    private final PostRepository postRepository;

    public GetPostUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity execute(UUID id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }
}
