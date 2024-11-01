package br.com.soupaulodev.blogspot.modules.post.controller.http;

import br.com.soupaulodev.blogspot.modules.post.controller.dto.CreatePostRequestDTO;
import br.com.soupaulodev.blogspot.modules.post.controller.dto.PostResponseDTO;
import br.com.soupaulodev.blogspot.modules.post.controller.dto.UpdatePostRequestDTO;
import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import br.com.soupaulodev.blogspot.modules.post.usecases.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final CreatePostUsecase createPostUsecase;
    private final GetAllPostsUsecase getAllPostsUsecase;
    private final GetPostUsecase getPostUsecase;
    private final SearchPostsUsecase searchPostsUsecase;
    private final UpdatePostUsecase updatePostUsecase;
    private final DeletePostUsecase deletePostUsecase;

    public PostController(
        CreatePostUsecase createPostUsecase,
        GetAllPostsUsecase getAllPostsUsecase,
        GetPostUsecase getPostUsecase,
        SearchPostsUsecase searchPostsUsecase,
        UpdatePostUsecase updatePostUsecase,
        DeletePostUsecase deletePostUsecase
    ) {
        this.createPostUsecase = createPostUsecase;
        this.getAllPostsUsecase = getAllPostsUsecase;
        this.getPostUsecase = getPostUsecase;
        this.searchPostsUsecase = searchPostsUsecase;
        this.updatePostUsecase = updatePostUsecase;
        this.deletePostUsecase = deletePostUsecase;
    }

    @PostMapping
    public ResponseEntity<PostResponseDTO> createPost(@Valid @RequestBody CreatePostRequestDTO request) {
        PostEntity postCreated = createPostUsecase.execute(request.toEntity("author"));
        URI uri = URI.create("/post/" + postCreated.getId());
        return ResponseEntity.created(uri).body(PostResponseDTO.fromEntity(postCreated));
    }

    @GetMapping
    public ResponseEntity<Page<PostResponseDTO>> getPosts(Pageable pageable) {
        Page<PostResponseDTO> posts = new PageImpl<>(
                getAllPostsUsecase.execute(pageable).getContent()
                        .stream().map(PostResponseDTO::fromEntity).toList(), pageable, 10);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable UUID id) {
        PostEntity post = getPostUsecase.execute(id);
        return ResponseEntity.ok().body(PostResponseDTO.fromEntity(post));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PostResponseDTO>> searchPosts(@RequestParam(required = false, defaultValue = "") String title,
                                                       @RequestParam(required = false, defaultValue = "") String author,
                                                       Pageable pageable) {
        Page<PostResponseDTO> posts = new PageImpl<>(
                searchPostsUsecase.execute(title, author, pageable).getContent()
                        .stream().map(PostResponseDTO::fromEntity).toList(), pageable, 10);
        return ResponseEntity.ok().body(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDTO> updatePost(@PathVariable UUID id, @Valid @RequestBody UpdatePostRequestDTO request) {
        PostEntity postUpdated = updatePostUsecase.execute(id, request.toEntity("author"));
        return ResponseEntity.ok().body(PostResponseDTO.fromEntity(postUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable UUID id) {
        deletePostUsecase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
