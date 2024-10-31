package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.exceptions.PostNotFoundException;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePostUsecase {

    private final PostRepository postRepository;

    public DeletePostUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void execute(UUID id) {
        postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(id);
    }
}
