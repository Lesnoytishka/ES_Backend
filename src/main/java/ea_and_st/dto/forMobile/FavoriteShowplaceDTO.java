package ea_and_st.dto.forMobile;

import com.ea_and_st.entities.Showplace;
import com.ea_and_st.utils.configs.MediaServerConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class FavoriteShowplaceDTO {
    private MediaServerConfig mediaServerConfig;
    private Long showplaceId;
    private String titleRu;
    private String titleEn;
    private BigDecimal popularity;
    private BigDecimal rating;
    private String mainPhotoPath;
    private String nearestMetro;
    private Integer category_1;
    private Integer category_2;

    public FavoriteShowplaceDTO(Showplace showplace) {
        this.showplaceId = showplace.getShowplaceId();
        this.titleRu = showplace.getTitleRu();
        this.titleEn = showplace.getTitleEn();
        this.popularity = showplace.getPopularity();
        this.rating = showplace.getRating();
        this.mainPhotoPath = mediaServerConfig.getPath() + showplace.getMainPhotoPath();
        this.nearestMetro = showplace.getNearestMetro();
        this.category_1 = showplace.getCategoryOfInterests1() == null? 0 : showplace.getCategoryOfInterests1();
        this.category_2 = showplace.getCategoryOfInterests2() == null ? 0 : showplace.getCategoryOfInterests2();
    }
}
