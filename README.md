IntelliJ 단축키 모음 
- ctrl + alt + v : 반환값 자동 생성 
- ctrl + alt + shift + t : 리팩토링 
- shift + f6 : 한번에 같은 단어 변경 
- ctrl + shift + t : 자동 테스트 패키지, 클래스, 메서드 생성 
- shift + f10 : 이전에 실행한걸 그대로 실행 
인터페이스 모음 
HashMap : Map 인터페이스의 대표적인 컬렉션. Map은 <key,value> 구조이며  key는 중복될 수 없다. 중복시 기존 키 사라진다 
-> 해싱을 사용하기 때문에 많은 양의 데이터를 검색하는데 효율이 좋다. 
-> put(key,value), remove(index), clear() : (모든요소제거), get 
-> 실무에서는 동시성 고려를 감안하여 ConcurrentHashMap, AtomicLong 사용한다. 

동작 과정 
                                      -------------------------------[스프링 부트]--------------------------------------
웹브라우저 -> url(localhost:8080/~) -> 내장 톰켓 서버 -> 스프링컨테이너 ( 컨트롤러 -> viewResolver(템플릿엔진 처리(html변환) ) ->  웹브라우저 

계층 구조

[ 컨트롤러 -> 서비스 -> 레포지토리 ]-> DB                       
------------도메인 접근-----------


@ResponsBody 
-> viewResolver를 사용하지 않고 http body 부분에 직접 값을 넣어주겠다고 명시하는 애노테이션 

문자면 String 값 반환 , 객체면 JSON값 반환 ( viewResolver 대신에 HttpMessageConverter가 동작)
<HttpMessageConverter>
문자 처리 : StringHttpMessageConverter
객체 처리 : MappingJackson2HttpMessageConverter 

<회원 관리 예제 > - 백엔드 
ㅁ 비즈니스 요구사항 
- 데이터 : 회원 id, 이름 
- 기능 : 회원 등록, 조회
- 데이터 저장소 선정 x 

아직 데이터가 선정되지 않아서 인터페이스로 구현 클래스를 변경할 수 있도록 설계 
MemberService -> MemberRepository(인터페이스) -> MemoryMemberRepository 

Optional : 값이 널이여도 감쌀수 있도록 해줌 
  
------ 테스트 --------
@AfterEach : 각 테스트가 종료 될때마다 실행  (테스트 간 의존관계를 없애기위한 clear 작업 처리 ) 
-> 테스트는 순서와 상관없이 독립적으로 실행되어야한다. (한번에 여러테스트 실행시 메모리 DB에 직전 테스트 결과가 남아있을 수 있다) 
  
@BeforeEach : 테스트 실행전에 설정   
  
ㅁ Assertions 사용으로 junit 테스트
import static org.assertj.core.api.Assertions.*;
assertThat(member).isEqualTo(result);
 
서비스 입장에서는 외부에서 memberRepository를 넣어줌 -> 이것을 DI 라고함 
memberService = new MemberService(memberRepository);
DI (필드, 세터, 생성자 3가지가 있지만 동적으로 변하는 경우가 거의 없기 때문에 생성자 주입을 권장) 
- 테스트 3단계 
  given : 뭔가 주어졌을 때 
  when : 이걸 실행했을 때
  then : 결과가 이게 나와야 돼 
  
-------------------------
보통 정형화된 컨트롤러, 서비스, 레포지토리는 컴포넌트 스캔 방식
정형화 되지 않거나, 상황에 따라 구현클래스를 변경해야 하면 설정을 통해 스프링 빈등록 ( 해당 프로젝트는 데이터 채택이안된 경우이므로 이 경우를 선택) 
  
 < 컴포넌트 스캔 방식 >
@Component : 스프링 빈으로 자동등록 (해당 애너테이션을 포함 : @Controller, @Service, @Repository ) 
  
@Autowired : 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어주는 애노테이션 
  -> 생성자에 @Autowired 사용 시 객체 생성 시점에 스프링 컨테이너에서 해당 빈을 찾아서 주입한다. 생성자가 1개일 경우 Autowired를 생략할 수 있다.
  
객체 의존관계를 외부에서 넣어주는것을 Dependency Injection : 의존선 주입이라고 한다. 
memberController -> memberService -> memberRepository : 연결 완료 

스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다(유일하게 하나만 록해서 공유한다) 
따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.

 < 자바 코드로 직접 스프링 빈 설정 >
@Service, Repository, Autowired 애노테이션을 제거하고 
@Configuration , @Bean 을 통해 직접 빈등록 할 수 있다. 
   
   
