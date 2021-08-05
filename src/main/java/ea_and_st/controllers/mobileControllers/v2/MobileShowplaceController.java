package ea_and_st.controllers.mobileControllers.v2;

import com.ea_and_st.dto.ShowplaceDTOAboutInfo;
import com.ea_and_st.dto.ShowplaceDtoForListConverter;
import com.ea_and_st.dto.UserAndShowplaceDTO;
import com.ea_and_st.dto.UserDTOWithIdAndLongitudeAndLatitude;
import com.ea_and_st.dto.forMobile.FavoriteShowplaceDTO;
import com.ea_and_st.dto.forMobile.ShowplaceDTOForListsAllInOne;
import com.ea_and_st.dto.forMobile.ShowplaceReviewDTO;
import com.ea_and_st.entities.ShowplaceReview;
import com.ea_and_st.services.facades.MobileService;
import com.ea_and_st.utils.annotations.MyLogger;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/v2/showplace")
@RequiredArgsConstructor
@Hidden
public class MobileShowplaceController {
    private final MobileService mobileService;

    @MyLogger
    @GetMapping("user_id/{userId}")
    public List<ShowplaceDTOForListsAllInOne> getAllListsForUserId(@PathVariable("userId") final Long userId) {
        return mobileService.getAllListsForUser(userId);
    }

    @MyLogger
    @PostMapping("lists")
    public List<ShowplaceDTOForListsAllInOne> getAllListsForUser(@RequestBody UserDTOWithIdAndLongitudeAndLatitude user) {
        return mobileService.getAllListsForUser(user.getUserId());
    }

    @MyLogger
    @PostMapping("review/save")
    public void saveShowplaceReview(@RequestBody ShowplaceReview showplaceReview) {
        mobileService.saveShowplaceReview(showplaceReview);
    }

    @MyLogger
    @GetMapping("review/get/{showplaceId}")
    public List<ShowplaceReviewDTO> getAllReviewsForShowplace(@PathVariable("showplaceId") Long showplaceId) {
        return mobileService.getAllReviewsForShowplace(showplaceId);
    }

    @MyLogger
    @PostMapping("review/delete")
    public void deleteShowplaceReview(ShowplaceReview showplaceReview) {
        mobileService.deleteShowplaceReview(showplaceReview);
    }

    @MyLogger
    @PostMapping("info/{showplaceId}")
    public ShowplaceDTOAboutInfo getInfoAboutShowplace(@RequestBody UserAndShowplaceDTO dto) {
        ShowplaceDTOAboutInfo returned = mobileService.getInfoAboutShowplace(dto.getShowplaceId());
        for (FavoriteShowplaceDTO showplace : mobileService.getFavoriteShowplaceList(dto.getUserId())) {
            if (dto.getShowplaceId().equals(showplace.getShowplaceId())) {
                returned.setFavorite(true);
                break;
            }
        }
        return returned;
    }

    @MyLogger
    @GetMapping("find/{subtext}")
    public List<ShowplaceDtoForListConverter> findShowplace(@PathVariable("subtext") String subtext) {
        return mobileService.findShowplace(subtext);
    }
}
