package br.com.soupaulodev.blogspot.modules.post.controllers;

import br.com.soupaulodev.blogspot.modules.post.controller.dto.CreatePostRequestDTO;
import br.com.soupaulodev.blogspot.modules.post.controller.dto.UpdatePostRequestDTO;
import br.com.soupaulodev.blogspot.modules.post.controller.http.PostController;
import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.usecases.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreatePostUsecase createPostUsecase;

    @MockBean
    private GetAllPostsUsecase getAllPostsUsecase;

    @MockBean
    private GetPostUsecase getPostUsecase;

    @MockBean
    private SearchPostsUsecase searchPostsUsecase;

    @MockBean
    private UpdatePostUsecase updatePostUsecase;

    @MockBean
    private DeletePostUsecase deletePostUsecase;

    @Autowired
    private ObjectMapper objectMapper;

    private PostEntity post;
    private UUID postId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        postId = UUID.randomUUID();
        post = new PostEntity(postId, "Sample Title", "Sample Resume", "Sample Content", "author", null, null);
    }

    @Test
    void shouldCreatePost() throws Exception {
        CreatePostRequestDTO request = new CreatePostRequestDTO("Sample Title", "Sample Resume", "Sample Content");
        when(createPostUsecase.execute(any(PostEntity.class))).thenReturn(post);

        mockMvc.perform(post("/api/v1/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/post/" + postId))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.author").value(post.getAuthor()));
    }

    @Test
    void shouldGetAllPosts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> postPage = new PageImpl<>(List.of(post), pageable, 1);
        when(getAllPostsUsecase.execute(any(Pageable.class))).thenReturn(postPage);

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value(post.getTitle()));
    }

    @Test
    void shouldGetPostById() throws Exception {
        when(getPostUsecase.execute(any(UUID.class))).thenReturn(post);

        mockMvc.perform(get("/api/v1/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void shouldSearchPosts() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PostEntity> postPage = new PageImpl<>(List.of(post), pageable, 1);
        when(searchPostsUsecase.execute(anyString(), anyString(), any(Pageable.class))).thenReturn(postPage);

        mockMvc.perform(get("/api/v1/posts/search")
                        .param("title", "Sample Title")
                        .param("author", "author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value(post.getTitle()));
    }

    @Test
    void shouldUpdatePost() throws Exception {
        UpdatePostRequestDTO request = new UpdatePostRequestDTO("Updated Title", "Updated Resume", "Updated Content");
        when(updatePostUsecase.execute(any(UUID.class), any(PostEntity.class))).thenReturn(post);

        mockMvc.perform(put("/api/v1/posts/{id}", postId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));
    }

    @Test
    void shouldDeletePost() throws Exception {
        doNothing().when(deletePostUsecase).execute(any(UUID.class));

        mockMvc.perform(delete("/api/v1/posts/{id}", postId))
                .andExpect(status().isNoContent());
    }
}
