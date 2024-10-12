package assigment.mastery.scheduleManagementJPA.domain.schedule;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SCHEDULE_MANAGER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ScheduleManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static ScheduleManager create(Schedule schedule, Member member) {
        return ScheduleManager.builder()
                .schedule(schedule)
                .member(member)
                .build();
    }
}
