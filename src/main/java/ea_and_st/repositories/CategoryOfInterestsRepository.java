package ea_and_st.repositories;

import com.ea_and_st.entities.CategoryOfInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryOfInterestsRepository extends JpaRepository <CategoryOfInterests, Integer> {
}
