package ea_and_st.repositories.showplace;

import com.ea_and_st.entities.ShowplaceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowplaceReviewRepository extends JpaRepository<ShowplaceReview, Long> {

    List<ShowplaceReview> getAllByUserIdAndActive(Long userId, boolean active);

    List<ShowplaceReview> getAllByShowplaceIdAndActiveOrderByAddDateDesc(Long showplaceId, boolean active);

    List<ShowplaceReview> getAllByShowplaceIdOrderByAddDateDesc(Long showplaceId);

    Optional<ShowplaceReview> getByShowplaceIdAndUserIdAndActive(Long showplaceId, Long userId, boolean active);
}
