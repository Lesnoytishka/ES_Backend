package ea_and_st.repositories.showplace;

import com.ea_and_st.entities.FavoriteShowplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteShowplaceRepository extends JpaRepository<FavoriteShowplace, Long> {

    boolean existsByUserIdAndShowplaceIdAndDeleted(Long userId, Long showplaceId, boolean isDeleted);

    FavoriteShowplace getByUserIdAndShowplaceIdAndDeleted(Long userId, Long showplaceId, boolean isDeleted);

    List<FavoriteShowplace> getAllByUserIdAndDeleted(Long userId, boolean isDeleted);

    List<FavoriteShowplace> getAllByShowplaceIdAndDeleted(Long showplaceId, boolean isDeleted);
}
