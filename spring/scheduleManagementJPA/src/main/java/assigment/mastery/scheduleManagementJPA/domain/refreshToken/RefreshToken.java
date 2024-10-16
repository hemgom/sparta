package assigment.mastery.scheduleManagementJPA.domain.refreshToken;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "REFRESH_TOKEN")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "REFRESH_TOKEN", nullable = false, length = 350)
    private String refreshToken;

    public static RefreshToken create(String refreshToken) {
        return RefreshToken.builder()
                .refreshToken(refreshToken)
                .build();
    }
}
