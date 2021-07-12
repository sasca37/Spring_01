package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
//테스트 시작 전에 트랜잭션을 실행하고 테스트 완료하면 롤백해서 DB에 영향을 주지 않는다.
// 커밋을 원하는 테스트에 @Commit으로 설정하여 DB에 데이터를 넣을 수 있다.
@Transactional
class MemberServiceIntegrationTest {

    //테스트 케이스는 다른데서 갖다 쓸게아니니까 필드 DI 하면 편하긴하다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given 뭔가 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then 결과가 이게 나와야해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member  member1 = new Member();
        member1.setName("spring");

        Member  member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


}