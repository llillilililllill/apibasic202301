package com.example.apibasic.jpabasic.repository;

import com.example.apibasic.jpabasic.entity.Gender;
import com.example.apibasic.jpabasic.entity.MemberEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.apibasic.jpabasic.entity.Gender.FEMALE;
import static org.junit.jupiter.api.Assertions.*;

// junit5에서는 클래스, 메서드, 필드 default제한만을 허용
@SpringBootTest //스프링 컨테이너를 사용해서 스프링이 관리하는 객체를 주입받는 기능
class MemberRepositoryTest {

    // 스프링 빈을 주입받을 때 필드주입을 사용
    @Autowired
    MemberRepository memberRepository;

    // @BeforEach - 각 테스트를 실행하기 전에 실행되는 내용
    @BeforeEach
    void bulkInsert() {
        MemberEntity saveMember1 = MemberEntity.builder()
                .account("zzz1")
                .password("1234")
                .nickname("꾸러기1")
                .gender(FEMALE)
                .build();

        MemberEntity saveMember2 = MemberEntity.builder()
                .account("zzz2")
                .password("1234")
                .nickname("꾸러기2")
                .gender(Gender.MALE)
                .build();

        MemberEntity saveMember3 = MemberEntity.builder()
                .account("zzz3")
                .password("1234")
                .nickname("꾸러기3")
                .gender(Gender.MALE)
                .build();

        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);
        memberRepository.save(saveMember3);

    }

    // 테스트 메서드
    // 테스트는 여러번 돌려도 성공한 테스트는 계속 성공해야 한다.
    // 단언 (Assertion) : 강력히 주장한다.
    @Test
    @DisplayName("회원의 가입 정보를 데이터베이스에 저장해야 한다.")
    @Transactional
    @Rollback   // 테스트가 끝나면 롤백해라
    void saveTest() {
        // given - when - then 패턴
        // given : 테스트시 주어지는 데이터
        MemberEntity saveMember = MemberEntity.builder()
                .account("zzz1234")
                .password("1234")
                .nickname("꾸러기")
                .gender(FEMALE)
                .build();
        
        // when : 실제 테스트 상황
        memberRepository.save(saveMember);  // insert쿼리 실행

        Optional<MemberEntity> foundMember = memberRepository.findById(1L);// pk기반 단일 행 조회

        // then : 테스트 결과 단언
        // 회원이 조회되었을 것이다
        MemberEntity member = foundMember.get();
        Assertions.assertNotNull(member);

        // 회원 테이블에 저장된 회원의 수는몇 명? => 1명

        // 저장된 회원의 user_code는 몇번? => 1번

//        // param1: 내 기대값, param2: 실제값
//        assertEquals(1L, member.getUserId());

        // 저장된 회원의 닉네임은? => 꾸러기
        assertEquals("꾸러기", member.getNickname());
    }

    // 목록 조회 테스트
    @Test
    @DisplayName("회워 목록을 조회하면 3명의 회원정보가 조회되어야 한다.")
    @Transactional
    @Rollback
    void findAllTest() {
        // given


        // when


        List<MemberEntity> memberEntityList = memberRepository.findAll();

        // then
        // 조회된 리스트의 사이즈는 3이다.
        assertEquals(3, memberEntityList.size());
        // 조회된 리스트의 두번쨰 회원 닉네임은 꾸러기2이다.
        assertEquals("꾸러기2", memberEntityList.get(1).getNickname());

        System.out.println("\n===========================================");
        memberEntityList.forEach(System.out::println);
        System.out.println("===========================================");
    }

    @Test
    @DisplayName("회원 데이터를 3개 등록하고 그 중 하나의 회원을 삭제해야 한다.")
    @Transactional
    @Rollback
    void deleteTest() {
        // given

        // when
        Long userCode = 2L;
        memberRepository.deleteById(userCode);
        Optional<MemberEntity> foundMember = memberRepository.findById(userCode);

        // then
        assertFalse(foundMember.isPresent());
        assertEquals(2, memberRepository.findAll().size());
    }

    @Test
    @DisplayName("2번 회원의 닉네임과 성별을 수정해야 한다.")
    @Transactional
    @Rollback
    void modifyTest() {
        // given
        Long userCode = 2L;
        String newNickName = "닭강정";
        Gender newGender = FEMALE;

        // when
        // JPA에서 수정은 조회 후 setter로 변경
        Optional<MemberEntity> foundMember = memberRepository.findById(userCode);
        foundMember.ifPresent(m -> {
            m.setNickname(newNickName);
            m.setGender(newGender);
        });

        Optional<MemberEntity> modifiedMember = memberRepository.findById(userCode);

        // then
        assertEquals("닭강정", modifiedMember.get().getNickname());
        assertEquals(FEMALE, modifiedMember.get().getGender());

    }
}