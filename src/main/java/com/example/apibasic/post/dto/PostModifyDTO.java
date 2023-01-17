package com.example.apibasic.post.dto;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PostModifyDTO {

    private String title;
    private String content;

}