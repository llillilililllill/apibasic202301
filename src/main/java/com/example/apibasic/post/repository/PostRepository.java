package com.example.apibasic.post.repository;

import com.example.apibasic.post.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // 제목으로 검색 후 페이징 처리
    Page<PostEntity> findByTitleContaining(String title, Pageable pageable);

}
