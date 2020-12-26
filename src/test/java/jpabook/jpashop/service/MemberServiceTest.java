package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
//    @Rollback(false) Rollback을 True로 하면, Insert문이 나가지 않음!
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Seo");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("Seo");

        Member member2 = new Member();
        member2.setName("Seo");

        memberService.join(member1);
        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2); //예외가 발생해야한다.
        });
    }
}