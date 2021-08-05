package ea_and_st.services.showplace;

import com.ea_and_st.dto.ShowplaceDTOAboutAll;
import com.ea_and_st.dto.ShowplaceDTOAboutAllEntity;
import com.ea_and_st.entities.Showplace;
import com.ea_and_st.entities.ShowplaceActiveTime;
import com.ea_and_st.entities.ShowplaceReview;
import com.ea_and_st.entities.ShowplaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowplaceAdminService {
    private final ShowplaceService showplaceService;
    private final ShowplaceActiveTimeService activeTimeService;
    private final ShowplaceTypeService typeService;
    private final ShowplaceReviewService reviewService;

    @Autowired
    public ShowplaceAdminService(ShowplaceService showplaceService, ShowplaceActiveTimeService activeTimeService, ShowplaceTypeService typeService, ShowplaceReviewService reviewService) {
        this.showplaceService = showplaceService;
        this.activeTimeService = activeTimeService;
        this.typeService = typeService;
        this.reviewService = reviewService;
    }

    public Showplace getShowplaceById(Long showplaceId) {
        return showplaceService.getById(showplaceId).get();
    }

    public List<ShowplaceDTOAboutAllEntity> getListOfShowplacesDTOAboutAll() {
        List<ShowplaceDTOAboutAllEntity> entities = new ArrayList<>();
        for (ShowplaceDTOAboutAll showplaceDTO: showplaceService.getAllForAdmin()) {
            ShowplaceDTOAboutAllEntity showplaceDTOAboutAllEntity = new ShowplaceDTOAboutAllEntity(showplaceDTO);
            String[] workTimes = activeTimeService.getAllWorkTimesByShowplaceId(showplaceDTO.getShowplaceId());
            showplaceDTOAboutAllEntity.setWorkTime1(workTimes[0]);
            showplaceDTOAboutAllEntity.setWorkTime2(workTimes[1]);
            showplaceDTOAboutAllEntity.setWorkTime3(workTimes[2]);
            showplaceDTOAboutAllEntity.setWorkTime4(workTimes[3]);
            showplaceDTOAboutAllEntity.setWorkTime5(workTimes[4]);
            showplaceDTOAboutAllEntity.setWorkTime6(workTimes[5]);
            showplaceDTOAboutAllEntity.setWorkTime7(workTimes[6]);
            showplaceDTOAboutAllEntity.setShowplaceType(showplaceService.getById(showplaceDTO.getShowplaceId()).get().getShowplaceType().getId());
            entities.add(showplaceDTOAboutAllEntity);
        }
        return entities;
    }

    public void saveInfoAboutShowplace(ShowplaceDTOAboutAllEntity showplaceDTOAboutAllEntity) {
        Showplace showplace = saveShowplace(showplaceDTOAboutAllEntity);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime1(), 1);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime2(), 2);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime3(), 3);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime4(), 4);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime5(), 5);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime6(), 6);
        saveShowplaceActiveTime(showplace.getShowplaceId(), showplaceDTOAboutAllEntity.getWorkTime7(), 7);
    }

    private Showplace saveShowplace(ShowplaceDTOAboutAllEntity showplaceDTOAboutAllEntity) {
        String serverName = "http://80.87.194.16:8998/api/media/v1/image/";
        Showplace showplace = new Showplace();
        showplace.setAddress(showplaceDTOAboutAllEntity.getAddress());
        showplace.setDescriptionRu(showplaceDTOAboutAllEntity.getDescriptionRu());
        showplace.setDescriptionEn(showplaceDTOAboutAllEntity.getDescriptionEn());
        showplace.setUrl(showplaceDTOAboutAllEntity.getUrl());
        showplace.setTitleRu(showplaceDTOAboutAllEntity.getTitleRu());
        showplace.setTitleEn(showplaceDTOAboutAllEntity.getTitleEn());
        showplace.setGpsLat(showplaceDTOAboutAllEntity.getGpsLat());
        showplace.setGpsLong(showplaceDTOAboutAllEntity.getGpsLong());
        showplace.setPopularity(showplaceDTOAboutAllEntity.getPopularity());
        showplace.setRating(showplaceDTOAboutAllEntity.getRating());
        showplace.setMainPhotoPath(serverName + showplaceDTOAboutAllEntity.getMainPhotoPath());
        showplace.setShowplaceType(typeService.getById(showplaceDTOAboutAllEntity.getShowplaceType()).get());
        return showplaceService.save(showplace);
    }

    private void saveShowplaceActiveTime(Long showplaceId, String workTime, int dayOfWeek) {
        ShowplaceActiveTime activeTime = new ShowplaceActiveTime();
        activeTime.setShowplaceId(showplaceId);
        activeTime.setWorkTime(workTime);
        activeTime.setDayOfWeek(dayOfWeek);
        activeTimeService.save(activeTime);
    }

    public List<ShowplaceType> getAllShowplacesTypes() {
        return typeService.getAll();
    }

    public List<ShowplaceActiveTime> getAllActiveTimesForShowplace(Long showplaceId) {
        return activeTimeService.getAllByShowplaceId(showplaceId);
    }

    public List<ShowplaceReview> getAllShowplaceReview(Long showplaceId) {
        return reviewService.getAllReviewsForShowplace(showplaceId);
    }

    public ShowplaceType getShowplaceType(Integer showplaceType) {
        return typeService.getById(showplaceType).get();
    }

    public List<Showplace> getAllShowplaces() {
        return showplaceService.getAll();
    }
}
