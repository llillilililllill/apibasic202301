package com.example.apibasic.post.api;

import com.example.apibasic.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 리소스 : 게시물 (Post)
/*
     게시물 목록 조회:  /posts       - GET
     게시물 개별 조회:  /posts/{id}  - GET
     게시물 등록:      /posts       - POST
     게시물 수정:      /posts/{id}  - PATCH
     게시물 삭제:      /posts/{id}  - DELETE
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostApiController {

    // PostRepository에게 의존하는 관계
    private final PostRepository postRepository;

    // 생성자 주입
    // @Autowired     // 스프링 컨테이너에게 의존객체를 자동주입해달라., 스프링 4 이후부터는 생성자가 1개일시 생략가능
//    public PostApiController(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    // SETTER 주입은 쓰면 안됨 (불변성 위반)
//    public void setPostRepository(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> list() {
        log.info("/posts GET request");
        return null;
    }

    // 게시물 개별 조회
    @GetMapping("/{postNo}")
    public ResponseEntity<?> detail(@PathVariable Long postNo) {
        log.info("/posts/{} GET request", postNo);
        return null;
    }

    // 게시물 등록
    @PostMapping
    public ResponseEntity<?> create() {
        log.info("/posts POST request");
        return null;
    }

    // 게시물 수정
    @PatchMapping("/{postNo}")
    public ResponseEntity<?> modify(@PathVariable Long postNo) {
        log.info("/posts/{} PATCH request", postNo);
        return null;
    }
    // 게시물 삭제
    @DeleteMapping("/{postNo}")
    public ResponseEntity<?> remove(@PathVariable Long postNo) {
        log.info("/posts/{} DELETE request", postNo);
        return null;
    }
}