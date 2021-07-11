package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
// 컨트롤 쉬프트 t : 자동 테스트 패키지, 클래스 , 메서드 생성
// 쉬프트 f10 : 이전에 실행 한걸 그대로 실행해줌
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 테스트 동작하기 전에
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        // 서비스 입장에서는 외부에서 memberRepository를 넣어줌 -> 이것을 DI 라고함
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    // 리포지토리를 꺼내기위해서

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
       /* try {
            memberService.join(member2);
            fail();
        }
        catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

        //then
    }

   @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}