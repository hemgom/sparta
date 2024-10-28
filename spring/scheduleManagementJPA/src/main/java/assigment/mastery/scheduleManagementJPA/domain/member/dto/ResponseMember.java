package assigment.mastery.scheduleManagementJPA.domain.member.dto;

import assigment.mastery.scheduleManagementJPA.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import static assigment.mastery.scheduleManagementJPA.converter.DateTimeFormatConverter.convertDateTimeFormat;

@Getter
@Builder
public class ResponseMember {
    private Long id;

    private String name;

    private String email;

    private String createAt;

    private String updateAt;

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
