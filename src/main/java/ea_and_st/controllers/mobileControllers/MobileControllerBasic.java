package ea_and_st.controllers.mobileControllers;

import com.ea_and_st.controllers.mobileControllers.v2.MobileFavoriteShowplaceController;
import com.ea_and_st.controllers.mobileControllers.v2.MobileShowplaceController;
import com.ea_and_st.controllers.mobileControllers.v2.MobileUserController;
import com.ea_and_st.dto.*;
import com.ea_and_st.dto.forMobile.FavoriteShowplaceDTO;
import com.ea_and_st.dto.forMobile.ShowplaceDTOForListsAllInOne;
import com.ea_and_st.dto.forMobile.ShowplaceReviewDTO;
import com.ea_and_st.entities.ShowplaceReview;
import com.ea_and_st.entities.User;
import com.ea_and_st.services.facades.MobileService;
import com.ea_and_st.utils.annotations.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/v1/")
@RequiredArgsConstructor
public class MobileControllerBasic {
    private final MobileService mobileService;
    private final MobileUserController userController;
    private final MobileShowplaceController showplaceController;
    private final MobileFavoriteShowplaceController favoriteShowplaceController;

    @GetMapping("user_id/{userId}")
    public List<ShowplaceDTOForListsAllInOne> getAllListsForUserId(@PathVariable("userId") Long userId) {
        return showplaceController.getAllListsForUserId(userId);
    }

    @PostMapping("lists")
    public List<ShowplaceDTOForListsAllInOne> getAllListsForUser(@RequestBody UserDTOWithIdAndLongitudeAndLatitude user) {
        return showplaceController.getAllListsForUser(user);
    }

    @GetMapping("favorites/get/{user_id}")
    public List<FavoriteShowplaceDTO> getFavoriteShowplaceList(@PathVariable("user_id") Long userId) {
        return favoriteShowplaceController.getFavoriteShowplaceList(userId);
    }

    @PostMapping(value = "favorites/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFavoriteShowplace(@RequestBody DTOUserAndShowplaceIds showplaceDTO) {
        favoriteShowplaceController.saveFavoriteShowplace(showplaceDTO);
    }

    @PostMapping("favorites/delete")
    public void deleteFavoriteShowplaceByUser(@RequestBody DTOUserAndShowplaceIds showplaceDTO) {
        favoriteShowplaceController.deleteFavoriteShowplaceByUser(showplaceDTO);
    }

    @PostMapping("showplace/review/save")
    public void saveShowplaceReview(@RequestBody ShowplaceReview showplaceReview) {
        showplaceController.saveShowplaceReview(showplaceReview);
    }

    @PostMapping("showplace/review/delete")
    public void deleteShowplaceReview(@RequestBody ShowplaceReview showplaceReview) {
        showplaceController.deleteShowplaceReview(showplaceReview);
    }

    @GetMapping("showplace/{showplaceId}/review/get")
    public List<ShowplaceReviewDTO> getAllReviewsForShowplace(@PathVariable("showplaceId") Long showplaceId) {
        return showplaceController.getAllReviewsForShowplace(showplaceId);
    }

    @PostMapping("showplace_info")
    public ShowplaceDTOAboutInfo getInfoAboutShowplace(@RequestBody UserAndShowplaceDTO dto) {
        return showplaceController.getInfoAboutShowplace(dto);
    }

    @PostMapping(value = "save_user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTOId saveUserAndReturnId(@RequestBody User user) {
        return userController.saveUserAndReturnId(user);
    }

    @PostMapping(value = "get_user", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTOId getUserId(@RequestBody User user) {
        return userController.getUserId(user);
    }

    @PostMapping(value = "save_user_questionnaire", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFirstUsersQuestionnaire(@RequestBody UserQuestionnaireDtoForMobile firstUserQuestionnaireDtoForMobile) {
        userController.saveFirstUsersQuestionnaire(firstUserQuestionnaireDtoForMobile);
    }

    @GetMapping("user/delete/{user_id}")
    public void setIsDeletedForUser(@PathVariable("user_id") Long userId) {
        userController.setIsDeletedForUser(userId);
    }

    @MyLogger
    @GetMapping("user/get/version/favorites/{user_id}")
    public Long getFavoritesListVersion(@PathVariable("user_id") Long userId) {
        return mobileService.getFavoritesListVersion(userId);
    }

    @MyLogger
    @GetMapping("user/get/version/lists/{user_id}")
    public Long getMergedListVersion(@PathVariable("user_id") Long userId) {
        return mobileService.getMergedListVersion(userId);
    }

    @GetMapping("showplace/find/{subtext}")
    public List<ShowplaceDtoForListConverter> findShowplace(@PathVariable("subtext") String subtext) {
        return showplaceController.findShowplace(subtext);
    }

    @PostMapping("/user/{userId}/photo")
    public @ResponseBody
    String saveUserPhoto(
            @PathVariable("userId") Long userId,
            @RequestParam("uploaded_file") MultipartFile file
    ) {
        return userController.saveUserPhoto(userId, file);
    }
}
