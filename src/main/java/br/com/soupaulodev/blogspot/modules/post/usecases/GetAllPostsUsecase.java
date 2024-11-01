package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllPostsUsecase {

    private final PostRepository postRepository;

    public GetAllPostsUsecase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Page<PostEntity> execute(Pageable pageable) {
        return postRepository.findAllByCreatedAt(pageable);
    }
}
