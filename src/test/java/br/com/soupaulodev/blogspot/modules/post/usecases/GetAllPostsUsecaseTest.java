package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetAllPostsUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetAllPostsUsecase getAllPostsUsecase;

    public GetAllPostsUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllPostsSuccessfully() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> posts = new PageImpl<>(List.of(new PostEntity()));
        when(postRepository.findAllByCreatedAt(pageable)).thenReturn(posts);

        Page<PostEntity> result = getAllPostsUsecase.execute(pageable);

        assertEquals(posts, result);
    }
}
