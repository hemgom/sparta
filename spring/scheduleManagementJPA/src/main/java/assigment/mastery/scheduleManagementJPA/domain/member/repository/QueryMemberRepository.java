package assigment.mastery.scheduleManagementJPA.domain.member.repository;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.UpdateMember;

public interface QueryMemberRepository {
    void update(Member member, UpdateMember request);
}
