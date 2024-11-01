package br.com.soupaulodev.blogspot.modules.post.usecases;

import br.com.soupaulodev.blogspot.modules.exceptions.PostNotFoundException;
import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class GetPostUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private GetPostUsecase getPostUsecase;

    public GetPostUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPostSuccessfully() {
        UUID id = UUID.randomUUID();
        PostEntity post = new PostEntity();
        when(postRepository.findById(id)).thenReturn(Optional.of(post));

        PostEntity result = getPostUsecase.execute(id);

        assertEquals(post, result);
    }

    @Test
    void shouldThrowPostNotFoundExceptionWhenPostNotFound() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> getPostUsecase.execute(id));
    }
}
