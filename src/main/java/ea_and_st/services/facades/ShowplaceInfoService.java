package ea_and_st.services.facades;

import com.ea_and_st.dto.ShowplaceDTOAboutInfo;
import com.ea_and_st.dto.ShowplaceDTOAboutInfoMainPart;
import com.ea_and_st.dto.ShowplaceDtoForListConverter;
import com.ea_and_st.entities.Showplace;
import com.ea_and_st.entities.ShowplaceActiveTime;
import com.ea_and_st.services.showplace.ShowplaceActiveTimeService;
import com.ea_and_st.services.showplace.ShowplaceService;
import com.ea_and_st.utils.enums.ColorOfWorkload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowplaceInfoService {
    private final ShowplaceService showplaceService;
    private final ShowplaceActiveTimeService activeTimeService;
    private final int MINIMAL_WORKFLOW_VALUE = 100_000;

    @Autowired
    public ShowplaceInfoService(
            ShowplaceService showplaceService,
            ShowplaceActiveTimeService activeTimeService
    ) {
        this.showplaceService = showplaceService;
        this.activeTimeService = activeTimeService;
    }

    public List<Showplace> getAll() {
        return showplaceService.getAll();
    }

    public ShowplaceDTOAboutInfo getDTOInformationById(Long id) {
        ShowplaceDTOAboutInfoMainPart part = showplaceService.getDTOAboutInfo(id);
        return new ShowplaceDTOAboutInfo.Builder()
                .setActiveTimes(activeTimeService.getAllByShowplaceId(id))
                .setWorkTime(activeTimeService.getAllWorkTimesByShowplaceId(id))
                .setColorId(setColorsForDaysWorkflow(id))
                .build(part);
    }

    private int[] setColorsForDaysWorkflow(Long id) {
        int[] colors = new int[7];
        int[] sums = new int[7];
        StringBuilder s;
        int min = MINIMAL_WORKFLOW_VALUE;
        int max = 0;
        for (int i = 0; i < colors.length; i++) {
            ShowplaceActiveTime activeTime = activeTimeService.getByShowplaceIdAndDay(id, i + 1).get();
            int sum =
                    activeTime.getAt10() +
                            activeTime.getAt11() +
                            activeTime.getAt12() +
                            activeTime.getAt13() +
                            activeTime.getAt14() +
                            activeTime.getAt15() +
                            activeTime.getAt16() +
                            activeTime.getAt17() +
                            activeTime.getAt18() +
                            activeTime.getAt19() +
                            activeTime.getAt20() +
                            activeTime.getAt21() +
                            activeTime.getAt22();
            sums[i] = sum;
            if (min > sum) min = sum;
            if (max < sum) max = sum;
        }
        int half = (max - min) / 2;
        int third = (half) / 3;

        for (int i = 0; i < sums.length; i++) {
            if ((activeTimeService.getByShowplaceIdAndDay(id, i + 1).get().getWorkTime() == null) ||
                    activeTimeService.getByShowplaceIdAndDay(id, i + 1).get().getWorkTime().equals("")) {
                colors[i] = ColorOfWorkload.ZERO_DATA.getValue();
            } else if (sums[i] < min + half + third) {
                colors[i] = ColorOfWorkload.MINIMAL_LOAD.getValue();
            } else if (sums[i] < min + half + (third * 2)) {
                colors[i] = ColorOfWorkload.MEDIUM_LOAD.getValue();
            } else {
                colors[i] = ColorOfWorkload.HIGHEST_LOAD.getValue();
            }
        }
        return colors;
    }

    public Optional<Showplace> getShowplaceById(Long showplaceId) {
        return showplaceService.getById(showplaceId);
    }

    public List<ShowplaceDtoForListConverter> findShowplace(String subtext) {
        return showplaceService.findShowplace(subtext);
    }

    public void saveShowplace(Showplace showplace) {
        showplaceService.save(showplace);
    }
}
