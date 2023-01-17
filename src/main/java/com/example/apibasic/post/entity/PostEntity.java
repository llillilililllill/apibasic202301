package com.example.apibasic.post.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

// 게시물의 데이터 자바빈즈
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tbl_post")
public class PostEntity {


//    public static long sequence = 1L;   // 연속된 일련번호

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_no")
    private Long postNo; // 게시물 식별번호
    @Column(nullable = false)
    private String writer; // 작성자
    @Column(nullable = false)
    private String title; // 제목
    private String content; // 내용
//    private List<String> hashTags; // 해시태그 목록
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime createDate; // 작성 시간
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime modifyDate; // 수정 시간

}