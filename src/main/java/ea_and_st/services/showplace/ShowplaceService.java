package ea_and_st.services.showplace;

import com.ea_and_st.dto.ShowplaceDTOAboutAll;
import com.ea_and_st.dto.ShowplaceDTOAboutInfoMainPart;
import com.ea_and_st.dto.ShowplaceDTOForLists;
import com.ea_and_st.dto.ShowplaceDtoForListConverter;
import com.ea_and_st.entities.Showplace;
import com.ea_and_st.repositories.showplace.ShowplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ShowplaceService {
    private final ShowplaceRepository repository;

    @Autowired
    public ShowplaceService(ShowplaceRepository repository) {
        this.repository = repository;
    }

    public Showplace save(Showplace showplace) {
        return repository.save(showplace);
    }

    public List<ShowplaceDTOForLists> getAllForDTO() {
        return repository.getAllByOrderByRatingAsc();
    }

    public List<ShowplaceDTOAboutAll> getAllForAdmin() {
        return repository.getAllByOrderByShowplaceId();
    }

    public List<ShowplaceDTOForLists> getShowplaceListMostPopular() {
        return repository.getAllByOrderByPopularityDesc();
    }

    public List<ShowplaceDTOForLists> getShowplaceListLeastPopular() {
        return repository.getAllByOrderByPopularityAsc();
    }

    public List<ShowplaceDTOForLists> getShowplaceListHighestRated() {
        return repository.getAllByOrderByRatingDesc();
    }

    public Showplace getDTOForListsById(Long showplaceId) {
        return repository.getByShowplaceId(showplaceId);
    }

    public Optional<Showplace> getById(Long id) {
        return repository.findById(id);
    }

    public ShowplaceDTOAboutInfoMainPart getDTOAboutInfo(Long id) {
        return repository.getByShowplaceIdOrderByShowplaceId(id);
    }

    public List<Showplace> getAll() {
        return repository.getAllByOrderByShowplaceIdAsc();
    }

    public List<ShowplaceDtoForListConverter> findShowplace(String subtext) {
        List<ShowplaceDtoForListConverter> converted = new ArrayList<>();
        for (ShowplaceDTOForLists dto : repository.getAllByTitleLowerRuContainsOrTitleEnContains(subtext.toLowerCase(Locale.ROOT), subtext)) {
            if (dto.getReleaseVersion() == 1) {
                converted.add(new ShowplaceDtoForListConverter(dto));
            }
        }
        return converted;
    }

    public boolean showplaceExistById(Long showplaceId) {
        return repository.existsById(showplaceId);
    }
}
