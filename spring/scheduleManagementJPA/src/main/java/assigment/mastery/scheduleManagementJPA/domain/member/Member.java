package assigment.mastery.scheduleManagementJPA.domain.member;

import assigment.mastery.scheduleManagementJPA.domain.member.dto.JoinMember;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.ResponseMember;
import assigment.mastery.scheduleManagementJPA.domain.member.dto.UpdateMember;
import assigment.mastery.scheduleManagementJPA.domain.schedule.Schedule;
import assigment.mastery.scheduleManagementJPA.domain.schedule.ScheduleManager;
import assigment.mastery.scheduleManagementJPA.security.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter.convertDateTimeFormat;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MEMBER")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @Column(name = "EMAIL", nullable = false, length = 350, unique = true)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Column(name = "CREATE_AT", nullable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "UPDATE_AT", nullable = false)
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "member")
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ScheduleManager> scheduleManagers = new ArrayList<>();

    public void update(UpdateMember request) {
        this.name = request.getName();
    }

    public static Member create(JoinMember joinMember, String encodedPassword, MemberRole role) {
        return Member.builder()
                .name(joinMember.getName())
                .email(joinMember.getEmail())
                .password(encodedPassword)
                .role(role)
                .build();
    }

    public static ResponseMember makeResponse(Member member) {
        return ResponseMember.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .createAt(convertDateTimeFormat(member.getCreateAt()))
                .updateAt(convertDateTimeFormat(member.getUpdateAt()))
                .build();
    }
}
