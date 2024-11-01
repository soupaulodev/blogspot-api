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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePostUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UpdatePostUsecase updatePostUsecase;

    public UpdatePostUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdatePostSuccessfully() {
        UUID id = UUID.randomUUID();
        PostEntity existingPost = new PostEntity();
        PostEntity updatedData = new PostEntity();
        updatedData.setTitle("New Title");

        when(postRepository.findById(id)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(existingPost)).thenReturn(existingPost);

        PostEntity result = updatePostUsecase.execute(id, updatedData);

        assertEquals("New Title", existingPost.getTitle());
        verify(postRepository).save(existingPost);
    }

    @Test
    void shouldThrowPostNotFoundExceptionWhenPostNotFound() {
        UUID id = UUID.randomUUID();
        PostEntity updatedData = new PostEntity();
        when(postRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> updatePostUsecase.execute(id, updatedData));
    }
}
