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

class SearchPostsUsecaseTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private SearchPostsUsecase searchPostsUsecase;

    public SearchPostsUsecaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnSearchResultsSuccessfully() {
        String title = "Test";
        String author = "Author";
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> posts = new PageImpl<>(List.of(new PostEntity()));
        when(postRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByCreatedAt(title, author, pageable))
                .thenReturn(posts);

        Page<PostEntity> result = searchPostsUsecase.execute(title, author, pageable);

        assertEquals(posts, result);
    }
}
