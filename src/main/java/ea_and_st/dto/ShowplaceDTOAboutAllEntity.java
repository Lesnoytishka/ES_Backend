package ea_and_st.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShowplaceDTOAboutAllEntity {
    private Long showplaceId;
    private Integer releaseVersion;

    private String titleRu;
    private String titleEn;

    private String descriptionRu;
    private String descriptionEn;

    private int showplaceType;

    private String url;
    private String address;
    private String nearestMetro;

    private BigDecimal gpsLat;
    private BigDecimal gpsLong;

    private BigDecimal popularity;
    private BigDecimal rating;

    private String mainPhotoPath;

    private String categoryOfInterests1;
    private String categoryOfInterests2;
    private String categoryOfInterests3;
    private String categoryOfInterests4;
    private String categoryOfInterests5;

    private Boolean open;

    private Boolean individualExcursions;
    private Boolean audioGuide;
    private BigDecimal audionGuidePrice;
    private String interactions;
    private String price;
    private String reducedPrice;
    private String coronavirusDescription;

    private String workTime1;
    private String workTime2;
    private String workTime3;
    private String workTime4;
    private String workTime5;
    private String workTime6;
    private String workTime7;

    public ShowplaceDTOAboutAllEntity(ShowplaceDTOAboutAll showplaceDTOAboutAll) {
        this.showplaceId = showplaceDTOAboutAll.getShowplaceId();
        this.titleRu = showplaceDTOAboutAll.getTitleRu();
        this.titleEn = showplaceDTOAboutAll.getTitleEn();
        this.descriptionRu = showplaceDTOAboutAll.getDescriptionRu();
        this.descriptionEn = showplaceDTOAboutAll.getDescriptionEn();
        this.url = showplaceDTOAboutAll.getUrl();
        this.address = showplaceDTOAboutAll.getAddress();
        this.gpsLat = showplaceDTOAboutAll.getGpsLat();
        this.gpsLong = showplaceDTOAboutAll.getGpsLong();
        this.popularity = showplaceDTOAboutAll.getPopularity();
        this.rating = showplaceDTOAboutAll.getRating();
        this.mainPhotoPath = showplaceDTOAboutAll.getMainPhotoPath();
    }

}
