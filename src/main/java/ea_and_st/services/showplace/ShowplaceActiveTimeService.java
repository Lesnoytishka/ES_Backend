package ea_and_st.services.showplace;

import com.ea_and_st.entities.ShowplaceActiveTime;
import com.ea_and_st.repositories.showplace.ShowplaceActiveTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowplaceActiveTimeService {
    private final ShowplaceActiveTimeRepository repository;

    @Autowired
    public ShowplaceActiveTimeService(ShowplaceActiveTimeRepository repository) {
        this.repository = repository;
    }

    public List<ShowplaceActiveTime> getAll() {
        return repository.findAll();
    }

    public ShowplaceActiveTime save(ShowplaceActiveTime showplaceActiveTime) {
        return repository.save(showplaceActiveTime);
    }

    public void delete(ShowplaceActiveTime showplaceActiveTime) {
        repository.delete(showplaceActiveTime);
    }

    public List<ShowplaceActiveTime> getAllByShowplaceId(Long showplaceId) {
        return repository.getAllByShowplaceIdOrderByDayOfWeek(showplaceId);
    }

    public String[] getAllWorkTimesByShowplaceId(Long id) {
        List<ShowplaceActiveTime> activeTimeList = repository.getAllByShowplaceIdOrderByDayOfWeek(id);
        String[] workTimes = new String[7];

        for (ShowplaceActiveTime s : activeTimeList) {
            workTimes[s.getDayOfWeek() - 1] = s.getWorkTime();
        }
        return workTimes;
    }

    public String[] getAllRecommendedVisitTimesByShowplaceId(Long id) {
        List<ShowplaceActiveTime> activeTimeList = repository.getAllByShowplaceIdOrderByDayOfWeek(id);
        String[] bestTimes = new String[7];

        for (ShowplaceActiveTime s : activeTimeList) {
            bestTimes[s.getDayOfWeek() - 1] = s.getVisitTime();
        }
        return bestTimes;
    }

    public Optional<ShowplaceActiveTime> getByShowplaceIdAndDay(Long showplaceId, int day) {
        return repository.getByShowplaceIdAndDayOfWeek(showplaceId, day);
    }
}
