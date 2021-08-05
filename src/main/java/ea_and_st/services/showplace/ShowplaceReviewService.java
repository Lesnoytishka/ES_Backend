package ea_and_st.services.showplace;

import com.ea_and_st.entities.ShowplaceReview;
import com.ea_and_st.repositories.showplace.ShowplaceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowplaceReviewService {
    private final ShowplaceReviewRepository repository;

    @Autowired
    public ShowplaceReviewService(ShowplaceReviewRepository repository) {
        this.repository = repository;
    }

    public List<ShowplaceReview> getAllActiveReviewsForShowplace(Long showplaceId) {
        return repository.getAllByShowplaceIdAndActiveOrderByAddDateDesc(showplaceId, true);
    }

    public List<ShowplaceReview> getAllReviewsForShowplace(Long showplaceId) {
        return repository.getAllByShowplaceIdOrderByAddDateDesc(showplaceId);
    }

    public ShowplaceReview save(ShowplaceReview showplaceReview) {
        return repository.save(showplaceReview);
    }

    public void deactivate(ShowplaceReview showplaceReview) {
        Optional<ShowplaceReview> review = repository.getByShowplaceIdAndUserIdAndActive(showplaceReview.getShowplaceId(), showplaceReview.getUserId(), true);
        if (review.isPresent()) {
            review.get().setActive(false);
            save(review.get());
        }
    }
}
