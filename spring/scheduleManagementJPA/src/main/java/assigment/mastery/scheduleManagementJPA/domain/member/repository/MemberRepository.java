package assigment.mastery.scheduleManagementJPA.domain.member.repository;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, QueryMemberRepository {
    @Query(value = "SELECT m FROM Member AS m WHERE m.name LIKE CONCAT('%', :name, '%')")
    Slice<Member> findAllByName(@Param("name") String name, Pageable pageable);

    @Query(value = "SELECT m FROM Member AS m WHERE m.name IN (:names)")
    List<Member> findAllByNameIn(@Param("names") List<String> names);

    @Query(value = "SELECT m FROM Member AS m WHERE m.email = :email")
    Optional<Member> findByEmail(@Param("email") String email);
}
