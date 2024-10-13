package assigment.mastery.scheduleManagementJPA.domain.member.repository;

import assigment.mastery.scheduleManagementJPA.domain.member.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
