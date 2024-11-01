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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeletePostUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private DeletePostUsecase deletePostUsecase;

    public DeletePostUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeletePostSuccessfully() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.of(new PostEntity()));

        deletePostUsecase.execute(id);

        verify(postRepository).deleteById(id);
    }

    @Test
    void shouldThrowPostNotFoundExceptionWhenPostNotFound() {
        UUID id = UUID.randomUUID();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> deletePostUsecase.execute(id));
    }
}
