package ea_and_st.services.showplace;

import com.ea_and_st.entities.ShowplaceType;
import com.ea_and_st.repositories.showplace.ShowplaceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowplaceTypeService {
    private final ShowplaceTypeRepository repository;

    @Autowired
    public ShowplaceTypeService(ShowplaceTypeRepository repository) {
        this.repository = repository;
    }

    public List<ShowplaceType> getAll() {
        return repository.findAll();
    }

    public Optional<ShowplaceType> getById(Integer id) {
        return repository.findById(id);
    }

    public ShowplaceType save(ShowplaceType showplaceType) {
        return repository.save(showplaceType);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
