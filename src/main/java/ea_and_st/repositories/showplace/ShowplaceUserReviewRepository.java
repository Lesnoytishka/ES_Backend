package ea_and_st.repositories.showplace;

import com.ea_and_st.entities.Showplace;
import com.ea_and_st.entities.ShowplaceUserReview;
import com.ea_and_st.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowplaceUserReviewRepository extends JpaRepository<ShowplaceUserReview, Long> {

    List<ShowplaceUserReview> getAllByUser(User user);

    List<ShowplaceUserReview> getAllByShowplace(Showplace showplace);
}
