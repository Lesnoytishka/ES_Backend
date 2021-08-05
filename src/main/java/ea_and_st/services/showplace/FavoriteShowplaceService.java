package ea_and_st.services.showplace;

import com.ea_and_st.entities.FavoriteShowplace;
import com.ea_and_st.repositories.showplace.FavoriteShowplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FavoriteShowplaceService {
    private final FavoriteShowplaceRepository repository;

    @Autowired
    public FavoriteShowplaceService(FavoriteShowplaceRepository repository) {
        this.repository = repository;
    }

    public Integer addOrRemoveShowplace(Long userId, Long showplaceId) {
        int actionKey;
        FavoriteShowplace favoriteShowplace;
        if (repository.existsByUserIdAndShowplaceIdAndDeleted(userId, showplaceId, false)) {
            favoriteShowplace = repository.getByUserIdAndShowplaceIdAndDeleted(userId, showplaceId, false);
            favoriteShowplace.setDeleted(true);
            favoriteShowplace.setDeleteTime(new Date());
            actionKey = 0;
        } else {
            favoriteShowplace = new FavoriteShowplace();
            favoriteShowplace.setUserId(userId);
            favoriteShowplace.setShowplaceId(showplaceId);
            favoriteShowplace.setAddTime(new Date());
            actionKey = 1;
        }
        repository.save(favoriteShowplace);
        return actionKey;
    }

    public void addShowplaceToFavoriteList(Long userId, Long showplaceId) {
        FavoriteShowplace favoriteShowplace;
        if (!repository.existsByUserIdAndShowplaceIdAndDeleted(userId, showplaceId, false)) {
            favoriteShowplace = new FavoriteShowplace();
            favoriteShowplace.setUserId(userId);
            favoriteShowplace.setShowplaceId(showplaceId);
            favoriteShowplace.setAddTime(new Date());
            repository.save(favoriteShowplace);
        }
    }

    public void removeShowplace(Long userId, Long showplaceId) {
        if (repository.existsByUserIdAndShowplaceIdAndDeleted(userId, showplaceId, false)) {
            FavoriteShowplace favoriteShowplace = repository.getByUserIdAndShowplaceIdAndDeleted(userId, showplaceId, false);
            repository.delete(favoriteShowplace);
        }
    }

    public List<FavoriteShowplace> getAllFavoriteShowplacesByUserId(Long userId) {
        return repository.getAllByUserIdAndDeleted(userId, false);
    }
}
