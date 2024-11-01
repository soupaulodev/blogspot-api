package br.com.soupaulodev.blogspot.modules.post.repositories;

import br.com.soupaulodev.blogspot.modules.post.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    @Query("SELECT p FROM PostEntity p ORDER BY p.createdAt DESC")
    Page<PostEntity> findAll(Pageable pageable);
    Page<PostEntity> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrderByCreatedAt(String title,
                                                                                  String author,
                                                                                  Pageable pageable);
}
