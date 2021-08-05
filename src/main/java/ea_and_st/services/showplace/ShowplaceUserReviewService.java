package ea_and_st.services.showplace;

import com.ea_and_st.entities.Showplace;
import com.ea_and_st.entities.ShowplaceUserReview;
import com.ea_and_st.repositories.showplace.ShowplaceUserReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowplaceUserReviewService {
    private final ShowplaceUserReviewRepository repository;

    @Autowired
    public ShowplaceUserReviewService(ShowplaceUserReviewRepository repository) {
        this.repository = repository;
    }

    public List<ShowplaceUserReview> getAll() {
        return repository.findAll();
    }

    public List<ShowplaceUserReview> getByShowplace(Showplace showplace) {
        return repository.getAllByShowplace(showplace);
    }
}
