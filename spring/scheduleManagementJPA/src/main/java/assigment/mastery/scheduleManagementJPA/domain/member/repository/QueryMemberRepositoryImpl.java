package assigment.mastery.scheduleManagementJPA.domain.member.repository;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.UpdateMember;
import org.springframework.stereotype.Repository;

@Repository
public class QueryMemberRepositoryImpl implements QueryMemberRepository {
    @Override
    public void update(Member member, UpdateMember request) {
        member.update(request);
    }
}
