package ea_and_st.repositories.showplace;

import com.ea_and_st.entities.ShowplaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowplaceTypeRepository extends JpaRepository<ShowplaceType, Integer> {
}
