package ea_and_st.services;

import com.ea_and_st.entities.CategoryOfInterests;
import com.ea_and_st.repositories.CategoryOfInterestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryOfInterestsService {
    private final CategoryOfInterestsRepository repository;

    @Autowired
    public CategoryOfInterestsService(CategoryOfInterestsRepository repository) {
        this.repository = repository;
    }

    public CategoryOfInterests save(CategoryOfInterests interest) {
        return repository.save(interest);
    }

    public Optional<CategoryOfInterests> getByID(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<CategoryOfInterests> getAll() {
        return repository.findAll(Sort.by(Sort.DEFAULT_DIRECTION,"id"));
    }

}