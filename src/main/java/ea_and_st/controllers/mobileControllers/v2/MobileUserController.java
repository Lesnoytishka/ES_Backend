package ea_and_st.controllers.mobileControllers.v2;

import com.ea_and_st.dto.UserDTOId;
import com.ea_and_st.dto.UserQuestionnaireDtoForMobile;
import com.ea_and_st.entities.User;
import com.ea_and_st.services.facades.MobileService;
import com.ea_and_st.utils.annotations.MyLogger;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/mobile/v2/user/")
@RequiredArgsConstructor
@Hidden
public class MobileUserController {
    private final MobileService mobileService;

    @MyLogger
    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTOId saveUserAndReturnId(@RequestBody User user) {
        try {
            return new UserDTOId(mobileService.saveUserAndReturnId(user));
        } catch (Exception e) {
            return new UserDTOId(-1L);
        }
    }

    @MyLogger
    @PostMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTOId getUserId(@RequestBody User user) {
        return new UserDTOId(mobileService.getUserByEmail(user.getEmail()));
    }

    @MyLogger
    @PostMapping(value = "questionnaire/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFirstUsersQuestionnaire(@RequestBody UserQuestionnaireDtoForMobile firstUserQuestionnaireDtoForMobile) {
        mobileService.saveFirstUsersQuestionnaire(firstUserQuestionnaireDtoForMobile);
    }

    @MyLogger
    @GetMapping("delete/{user_id}")
    public void setIsDeletedForUser(@PathVariable("user_id") Long userId) {
        mobileService.setIsDeletedForUser(userId);
    }

    @MyLogger
    @PostMapping("/user/{userId}/photo")
    public @ResponseBody
    String saveUserPhoto(
            @PathVariable("userId") Long userId,
            @RequestParam("uploaded_file") MultipartFile file
    ) {
        return mobileService.uploadPhoto(userId, file);
    }
}
