package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CreatePostUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CreatePostUsecase createPostUsecase;

    public CreatePostUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePostSuccessfully() {
        PostEntity post = new PostEntity();
        when(postRepository.save(post)).thenReturn(post);

        PostEntity createdPost = createPostUsecase.execute(post);

        verify(postRepository).save(post);
        assertEquals(post, createdPost);
    }
}
