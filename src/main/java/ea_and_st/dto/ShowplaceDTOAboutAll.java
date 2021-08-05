package ea_and_st.dto;

import java.math.BigDecimal;

public interface ShowplaceDTOAboutAll {

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

    String getMainPhotoPath();
}
