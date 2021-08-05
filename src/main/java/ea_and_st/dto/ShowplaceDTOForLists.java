package ea_and_st.dto;

import java.math.BigDecimal;

public interface ShowplaceDTOForLists {

    Long getShowplaceId();

    String getTitleRu();
    String getTitleEn();

    String getDescriptionRu();
    String getDescriptionEn();

    String getUrl();
    String getAddress();

    BigDecimal getGpsLat();
    BigDecimal getGpsLong();

    BigDecimal getPopularity();
    BigDecimal getRating();

    String getNearestMetro();
    String getMainPhotoPath();

    Integer getCategoryOfInterests1();
    Integer getCategoryOfInterests2();

    Integer getReleaseVersion();
}
