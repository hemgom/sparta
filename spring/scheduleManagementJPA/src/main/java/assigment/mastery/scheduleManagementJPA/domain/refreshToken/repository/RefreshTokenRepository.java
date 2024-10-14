package assigment.mastery.scheduleManagementJPA.domain.refreshToken.repository;

import assigment.mastery.scheduleManagementJPA.domain.refreshToken.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Query(value = "SELECT r FROM RefreshToken AS r WHERE r.refreshToken = :refreshToken")
    Optional<RefreshToken> findByRefreshToken(@Param("refreshToken") String refreshToken);

    @Modifying
    @Query(value = "DELETE FROM RefreshToken AS r WHERE r.refreshToken = :refreshToken")
    void deleteByRefreshToken(@Param("refreshToken") String refreshToken);
}
