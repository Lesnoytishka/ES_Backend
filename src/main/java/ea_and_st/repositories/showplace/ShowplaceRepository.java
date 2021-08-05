package ea_and_st.repositories.showplace;

import com.ea_and_st.dto.ShowplaceDTOAboutAll;
import com.ea_and_st.dto.ShowplaceDTOAboutInfoMainPart;
import com.ea_and_st.dto.ShowplaceDTOForLists;
import com.ea_and_st.entities.Showplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowplaceRepository extends JpaRepository<Showplace, Long> {

    List<Showplace> getAllByOrderByShowplaceIdAsc();

    List<ShowplaceDTOForLists> getAllByOrderByPopularityAsc();

    List<ShowplaceDTOForLists> getAllByOrderByPopularityDesc();

    List<ShowplaceDTOForLists> getAllByOrderByRatingAsc();

    List<ShowplaceDTOForLists> getAllByOrderByRatingDesc();

    Showplace getByShowplaceId(Long id);

    ShowplaceDTOAboutInfoMainPart getByShowplaceIdOrderByShowplaceId(Long id);

    List<ShowplaceDTOAboutAll> getAllByOrderByShowplaceId();

    List<ShowplaceDTOForLists> getAllByTitleLowerRuContainsOrTitleEnContains(String textRu, String textEn);
}
