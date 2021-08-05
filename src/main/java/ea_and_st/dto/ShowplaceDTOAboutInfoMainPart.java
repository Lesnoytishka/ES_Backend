package ea_and_st.dto;

import java.math.BigDecimal;

public interface ShowplaceDTOAboutInfoMainPart {

    Long getShowplaceId();

    String getTitleRu();
    String getTitleEn();

    String getAddress();
    BigDecimal getGpsLat();
    BigDecimal getGpsLong();

    Float getPopularity();
    Float getRating();

    String getMainPhotoPath();

    String getDescriptionRu();
    String getDescriptionEn();

    String getPriceGroups();
    String getPriceCost();

    String getPrice();
    String getReducedPrice();

    Integer getCategoryOfInterests1();
    Integer getCategoryOfInterests2();

    String getNearestMetro();
}
