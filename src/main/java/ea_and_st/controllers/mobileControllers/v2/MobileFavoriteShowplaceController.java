package ea_and_st.controllers.mobileControllers.v2;

import com.ea_and_st.dto.DTOUserAndShowplaceIds;
import com.ea_and_st.dto.forMobile.FavoriteShowplaceDTO;
import com.ea_and_st.services.facades.MobileService;
import com.ea_and_st.utils.annotations.MyLogger;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobile/v2/favorites")
@RequiredArgsConstructor
@Hidden
public class MobileFavoriteShowplaceController {
    private final MobileService mobileService;

    @MyLogger
    @GetMapping("get/{user_id}")
    public List<FavoriteShowplaceDTO> getFavoriteShowplaceList(@PathVariable("user_id") Long userId) {
        return mobileService.getFavoriteShowplaceList(userId);
    }

    @MyLogger
    @PostMapping(value = "save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveFavoriteShowplace(@RequestBody DTOUserAndShowplaceIds showplaceDTO) {
        mobileService.saveFavoriteShowplace(showplaceDTO);
    }

    @MyLogger
    @PostMapping("delete")
    public void deleteFavoriteShowplaceByUser(@RequestBody DTOUserAndShowplaceIds showplaceDTO) {
        mobileService.deleteFavoriteShowplace(showplaceDTO);
    }
}
