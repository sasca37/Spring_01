package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 스프링이 올라올 때 얘를 서비스라고 인식하고 등록하고 생성자를 호출
public class MemberService {

    private final MemberRepository memberRepository;

    // MemberRepository도 Autowired 때문에 주입됨, 의존관계설정해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
          회원 가입
    */
    public Long join(Member member){
        //같은 이름이 있는 중복 회원X
        //컨트롤 알트 v : 반환값 자동 생성

        // Optional 안에 감쌌기 때문에 중복일경우 밑에 메서드와 같이 해결이 가능하다.
        // 컨트롤 알트 쉬프트 t : 리팩토링 
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /*
        전체 회원 조회
     */
    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
