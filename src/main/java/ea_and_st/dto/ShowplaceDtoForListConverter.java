package ea_and_st.dto;

import com.ea_and_st.entities.Showplace;
import com.ea_and_st.utils.configs.MediaServerConfig;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ShowplaceDtoForListConverter {
    private MediaServerConfig mediaServerConfig = new MediaServerConfig();
    private Long showplaceId;

    private String titleRu;
    private String titleEn;

    private String descriptionRu;
    private String descriptionEn;

    private String url;
    private String address;

    private BigDecimal gpsLat;
    private BigDecimal gpsLong;

    private BigDecimal popularity;
    private BigDecimal rating;

    private Integer category1;
    private Integer category2;

    private String nearestMetro;
    private String mainPhotoPath;

    private Integer releaseVersion;

    public ShowplaceDtoForListConverter(ShowplaceDTOForLists showplaceDTOForLists) {
        this.showplaceId = showplaceDTOForLists.getShowplaceId();
        this.titleRu = showplaceDTOForLists.getTitleRu();
        this.titleEn = showplaceDTOForLists.getTitleEn();
        this.descriptionRu = showplaceDTOForLists.getDescriptionRu();
        this.descriptionEn = showplaceDTOForLists.getDescriptionEn();
        this.url = showplaceDTOForLists.getUrl();
        this.address = showplaceDTOForLists.getAddress();
        this.gpsLat = showplaceDTOForLists.getGpsLat();
        this.gpsLong = showplaceDTOForLists.getGpsLong();
        this.popularity = showplaceDTOForLists.getPopularity();
        this.rating = showplaceDTOForLists.getRating();
        this.nearestMetro = showplaceDTOForLists.getNearestMetro();
        this.mainPhotoPath = mediaServerConfig.getPath() + showplaceDTOForLists.getMainPhotoPath();
        this.releaseVersion = showplaceDTOForLists.getReleaseVersion();
        this.category1 = showplaceDTOForLists.getCategoryOfInterests1() != null ? showplaceDTOForLists.getCategoryOfInterests1() : 0;
        this.category2 = showplaceDTOForLists.getCategoryOfInterests2() != null ? showplaceDTOForLists.getCategoryOfInterests2() : 0;
    }

    public ShowplaceDtoForListConverter(Showplace showplace) {
        this.showplaceId = showplace.getShowplaceId();
        this.titleRu = showplace.getTitleRu();
        this.titleEn = showplace.getTitleEn();
        this.descriptionRu = showplace.getDescriptionRu();
        this.descriptionEn = showplace.getDescriptionEn();
        this.url = showplace.getUrl();
        this.address = showplace.getAddress();
        this.gpsLat = showplace.getGpsLat();
        this.gpsLong = showplace.getGpsLong();
        this.popularity = showplace.getPopularity();
        this.rating = showplace.getRating();
        this.nearestMetro = showplace.getNearestMetro();
        this.mainPhotoPath = mediaServerConfig.getPath() + showplace.getMainPhotoPath();
        this.releaseVersion = showplace.getReleaseVersion();
        this.category1 = showplace.getCategoryOfInterests1() != null ? showplace.getCategoryOfInterests1() : 0;
        this.category2 = showplace.getCategoryOfInterests2() != null ? showplace.getCategoryOfInterests2() : 0;
    }
}
