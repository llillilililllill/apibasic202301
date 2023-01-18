package com.example.apibasic.jpa_relation.entity;

import lombok.*;

import javax.persistence.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "empId")
@Entity
@Builder
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId; // 사원번호
    private String empName; // 사원명

    /*
        EAGER : 항상 조인을 하도록 설정
        LAZY : 부서정보가 필요할때만 조인 (실무에서는 ManyToOne시 무조건 LAZY)
     */
    @ManyToOne(fetch = FetchType.LAZY)  // 연관관계 매핑
    @JoinColumn(name = "dept_id")   // FK 컬럼 이름 지정
    private Department department; // 부서코드

}
