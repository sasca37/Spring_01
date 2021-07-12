package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스는 다중상속 가능, 인터페이스는 extends로 상속만 가능
// JpaRepository를 통해 자동 빈 등록 및 CRUD 기능 제공

//실무에서는 해결하기 어려운 쿼리는 JPA에서 제공하는 네이티브 쿼리를 사용하거나 JdbcTemplate을 사용하면 된다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
