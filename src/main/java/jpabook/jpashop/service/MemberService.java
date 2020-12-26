package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
/**
 * lombok을 이용할 때,
 * @AllArgsConstructor --> 생성자를 만들어준다.
 * @RequiredArgsConstructor --> final이 있는 필드를 가지고 생성자를 만들어준다.
 */
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired //생성자 주입, 생성자가 단 하나만 있는 경우에는 Autowired가 없어도 주입됨.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 검증
     *
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        // 동시에 같은 이름으로 validation이 들어올 수 있다.
        // 따라서 멀티스레드 환경에서 대비하기 위해 DB를 설계할 때 Name을 UNIQUE 조건을 걸어주는 것이 좋다.
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("[ERROR] 이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 단건 조회
     *
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
