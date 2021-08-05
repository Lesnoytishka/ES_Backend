package ea_and_st.services.facades;

import com.ea_and_st.api.AnalyticalClient;
import com.ea_and_st.dto.DTOUserAndShowplaceIds;
import com.ea_and_st.dto.ShowplaceDTOAboutInfo;
import com.ea_and_st.dto.ShowplaceDtoForListConverter;
import com.ea_and_st.dto.UserQuestionnaireDtoForMobile;
import com.ea_and_st.dto.forMobile.FavoriteShowplaceDTO;
import com.ea_and_st.dto.forMobile.FormForLists;
import com.ea_and_st.dto.forMobile.ShowplaceDTOForListsAllInOne;
import com.ea_and_st.dto.forMobile.ShowplaceReviewDTO;
import com.ea_and_st.entities.*;
import com.ea_and_st.services.CategoryOfInterestsService;
import com.ea_and_st.services.UserService;
import com.ea_and_st.services.showplace.FavoriteShowplaceService;
import com.ea_and_st.services.showplace.ShowplaceReviewService;
import com.ea_and_st.utils.annotations.MySubLogger;
import com.ea_and_st.utils.enums.ApiSource;
import com.ea_and_st.utils.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MobileService {
    private final CategoryOfInterestsService categoryOfInterestsService;
    private final FavoriteShowplaceService favoriteShowplaceService;
    private final ShowplaceInfoService showplaceService;
    private final UserService userService;
    private final AnalyticalClient analyticalClient;
    private final ShowplaceReviewService showplaceReviewService;

    @Autowired
    public MobileService(CategoryOfInterestsService categoryOfInterestsService, FavoriteShowplaceService favoriteShowplaceService, ShowplaceInfoService showplaceService, UserService userService, AnalyticalClient analyticalClient, ShowplaceReviewService showplaceReviewService) {
        this.categoryOfInterestsService = categoryOfInterestsService;
        this.favoriteShowplaceService = favoriteShowplaceService;
        this.showplaceService = showplaceService;
        this.userService = userService;
        this.analyticalClient = analyticalClient;
        this.showplaceReviewService = showplaceReviewService;
    }

    public Long getMergedListVersion(Long userId) {
        return userService.getUserById(userId).get().getVersionLists();
    }

    public List<ShowplaceDTOForListsAllInOne> getAllListsForUser(Long userId) {
        List<ShowplaceDTOForListsAllInOne> allListsDTO = new FormForLists().mergeLists(
                getShowplaceListForYou(userId),
                getShowplaceListMostPopular(),
                getShowplaceListLeastPopular(userId),
                getShowplaceListSmart(userId),
                getFavoriteShowplaceList(userId)
        );
        userService.updateListsVersionAndSaveUser(userService.getUserById(userId).get());
        return allListsDTO;
    }

    @MySubLogger(source = ApiSource.JAVA_DATABASE)
    public List<ShowplaceDtoForListConverter> getShowplaceListForYou(Long userId) {
        List<ShowplaceDtoForListConverter> list = new ArrayList<>();
        for (Long showplaceId : analyticalClient.getPlaceForYouWithUsingUserId(userId)) {
            addShowplaceToList(list, showplaceId);
        }
        return list;
    }

    @MySubLogger(source = ApiSource.JAVA_DATABASE)
    public List<ShowplaceDtoForListConverter> getShowplaceListMostPopular() {
        List<ShowplaceDtoForListConverter> list = new ArrayList<>();
        for (Long showplaceId : analyticalClient.getPopularPlaces()) {
            addShowplaceToList(list, showplaceId);
        }
        return list;
    }

    @MySubLogger(source = ApiSource.JAVA_DATABASE)
    public List<ShowplaceDtoForListConverter> getShowplaceListLeastPopular(Long userId) {
        List<ShowplaceDtoForListConverter> list = new ArrayList<>();
        for (Long showplaceId : analyticalClient.getNonPopularPlacesWithUsingUserId(userId)) {
            addShowplaceToList(list, showplaceId);
        }
        return list;
    }

    @MySubLogger(source = ApiSource.JAVA_DATABASE)
    public List<ShowplaceDtoForListConverter> getShowplaceListSmart(Long userId) {
        List<ShowplaceDtoForListConverter> list = new ArrayList<>();
        for (Long showplaceId : analyticalClient.getSmartPlacesWithUsingUserId(userId)) {
            addShowplaceToList(list, showplaceId);
        }
        return list;
    }

    private void addShowplaceToList(List<ShowplaceDtoForListConverter> list, Long showplaceId) {
        Optional<Showplace> showplaceOptional = showplaceService.getShowplaceById(showplaceId);
        if (showplaceOptional.isPresent() && showplaceOptional.get().getReleaseVersion() == 1) {
            ShowplaceDtoForListConverter converter = new ShowplaceDtoForListConverter(showplaceOptional.get());
            list.add(converter);
        }
    }

//    --------------------
//    --    SHOWPLACE   --
//    --------------------

    public ShowplaceDTOAboutInfo getInfoAboutShowplace(Long showplaceId) {
        return showplaceService.getDTOInformationById(showplaceId);
    }

//    --------------------
//    --      USER      --
//    --------------------

    @Transactional
    public Long saveUserAndReturnId(User user) {
        user.setId(new Date().getTime());
        user.setLanguage("ru");
        user.setVersionFavorites(0L);
        user.setVersionLists(0L);
        user.setIsDeleted(false);
        if (userService.userExist(user.getId()) || userService.userExistByEmail(user.getEmail())) {
            return 0L;
        } else {
            userService.save(user);
            return user.getId();
        }
    }

    public Long getUserByEmail(String email) {
        try {
            return userService.getUserByEmailAndIsDeleted(email, false).orElseThrow(UserNotFoundException::new).getId();
        } catch (UserNotFoundException e) {
            log.warn("User not found [ " + email + " ]");
        }
        return 0L;
    }

    public void saveFirstUsersQuestionnaire(UserQuestionnaireDtoForMobile firstUserQuestionnaireDtoForMobile) {
        User user = userService.getUserById(firstUserQuestionnaireDtoForMobile.getUserId()).get();
        user.setGender(firstUserQuestionnaireDtoForMobile.getGender().charAt(0));
        user.setName(firstUserQuestionnaireDtoForMobile.getName());
        user.setBirthYear(firstUserQuestionnaireDtoForMobile.getBirthYear());
        user.setLocationType(firstUserQuestionnaireDtoForMobile.getLocationType());
        List<CategoryOfInterests> categoryOfInterests = new ArrayList<>();
        for (Integer categoryOfInterestId : firstUserQuestionnaireDtoForMobile.getInterests()) {
            categoryOfInterests.add(categoryOfInterestsService.getByID(categoryOfInterestId).get());
        }
        user.setCategoryOfInterests(categoryOfInterests);
        userService.save(user);
    }

    public void setIsDeletedForUser(Long userId) {
        userService.setIsDeletedForUser(userId);
    }

//    --------------------
//    --    FAVORITES   --
//    --------------------

    public Long getFavoritesListVersion(Long userId) {
        return userService.getUserById(userId).get().getVersionFavorites();
    }

    public List<FavoriteShowplaceDTO> getFavoriteShowplaceList(Long userId) {
        List<FavoriteShowplaceDTO> list = new ArrayList<>();
        for (FavoriteShowplace favoriteShowplace : favoriteShowplaceService.getAllFavoriteShowplacesByUserId(userId)) {
            FavoriteShowplaceDTO favoriteShowplaceDTO = new FavoriteShowplaceDTO(showplaceService.getShowplaceById(favoriteShowplace.getShowplaceId()).get());
            list.add(favoriteShowplaceDTO);
        }
        return list;
    }

    public void saveFavoriteShowplace(DTOUserAndShowplaceIds favoriteDTO) {
        favoriteShowplaceService.addShowplaceToFavoriteList(favoriteDTO.getUserId(), favoriteDTO.getShowplaceId());
        userService.updateFavoritesVersionAndSaveUser(userService.getUserById(favoriteDTO.getUserId()).get());
    }

    public void deleteFavoriteShowplace(DTOUserAndShowplaceIds favoriteDTO) {
        favoriteShowplaceService.removeShowplace(favoriteDTO.getUserId(), favoriteDTO.getShowplaceId());
        userService.updateFavoritesVersionAndSaveUser(userService.getUserById(favoriteDTO.getUserId()).get());
    }

    public void saveShowplaceReview(ShowplaceReview showplaceReview) {
        showplaceReview.setAddDate(new Date());
        showplaceReview.setActive(true);
        showplaceReviewService.save(showplaceReview);
        recalculateRating(showplaceReview.getShowplaceId());
    }

    public List<ShowplaceReviewDTO> getAllReviewsForShowplace(Long showplaceId) {
        List<ShowplaceReviewDTO> showplaceReviewDTOS = new ArrayList<>();
        List<ShowplaceReview> showplaceReviews = showplaceReviewService.getAllActiveReviewsForShowplace(showplaceId);
        for (ShowplaceReview showplaceReview : showplaceReviews) {
            ShowplaceReviewDTO showplaceReviewDTO = new ShowplaceReviewDTO();
            showplaceReviewDTO.setReviewId(showplaceReview.getId());
            showplaceReviewDTO.setUserName(
                    userService.getUserById(showplaceReview.getUserId()).isPresent() ?
                            userService.getUserById(showplaceReview.getUserId()).get().getName()
                            : "Неизестный пользователь"
            );
            showplaceReviewDTO.setUserPhotoUri(
                    userService.getUserById(showplaceReview.getUserId()).isPresent() ?
                            userService.getUserById(showplaceReview.getUserId()).get().getPhoto()
                            : "http://80.87.194.16:8998/api/media/v1/image/profile40.png"
            );
            showplaceReviewDTO.setRating(showplaceReview.getReview());
            showplaceReviewDTO.setContent(showplaceReview.getContent());
            showplaceReviewDTO.setAddDate(showplaceReview.getAddDate());
            showplaceReviewDTOS.add(showplaceReviewDTO);
        }
        return showplaceReviewDTOS;
    }

    public void deleteShowplaceReview(ShowplaceReview showplaceReview) {
        showplaceReviewService.deactivate(showplaceReview);
    }

    public List<ShowplaceDtoForListConverter> findShowplace(String subtext) {
        return showplaceService.findShowplace(subtext);
    }

    public void recalculateRating(Long showplaceId) {
        float returned;
        double allRatingsSum = 0.0;
        List<ShowplaceReview> reviews = showplaceReviewService.getAllActiveReviewsForShowplace(showplaceId);
        for (ShowplaceReview review : reviews) {
            allRatingsSum += review.getReview().doubleValue();
        }

        double rating = reviews.size() == 0 ? 0.0 : allRatingsSum / reviews.size();
        int ratingLeft = (int) rating;
        double ratingRight = rating - ratingLeft;
        if (ratingRight == 0) {
            returned = ratingLeft;
        } else if (ratingRight < 0.3) {
            returned = ratingLeft + 0.25f;
        } else if (ratingRight < 0.7) {
            returned = ratingLeft + 0.5f;
        } else {
            returned = ratingLeft + 0.75f;
        }

        Showplace showplace = showplaceService.getShowplaceById(showplaceId).get();
        showplace.setRating(new BigDecimal(returned));
        showplaceService.saveShowplace(showplace);
    }

    public String uploadPhoto(Long userId, MultipartFile file) {
        if (!file.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream("../media/" + userId + ".jpg")) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(fos);
                stream.write(bytes);
                stream.close();
                return "Вы удачно загрузили Фотографию в " + userId + ".jpg !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + userId + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + userId + " потому что файл пустой.";
        }
    }
}
