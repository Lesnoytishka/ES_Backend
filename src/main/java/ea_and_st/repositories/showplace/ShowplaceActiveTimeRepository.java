package ea_and_st.repositories.showplace;

import com.ea_and_st.entities.ShowplaceActiveTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowplaceActiveTimeRepository extends JpaRepository<ShowplaceActiveTime, Long> {
    List<ShowplaceActiveTime> getAllByShowplaceIdOrderByDayOfWeek(Long id);
    Optional<ShowplaceActiveTime> getByShowplaceIdAndDayOfWeek(Long showplaceId, int day);
}
