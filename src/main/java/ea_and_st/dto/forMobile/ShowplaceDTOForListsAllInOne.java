package ea_and_st.dto.forMobile;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShowplaceDTOForListsAllInOne {

    private Long id;

    private String titleRu;
    private String titleEn;

    private BigDecimal gpsLat;
    private BigDecimal gpsLong;

    private Float popularity;
    private Float rating;

    private String nearestMetro;
    private String mainPhotoPath;

    private boolean forYou;
    private boolean popular;
    private boolean uncrowded;
    private boolean nearest;
    private boolean favorite;

    private Integer category_1;
    private Integer category_2;

    public static class Builder {
        private Long id;

        private String titleRu;
        private String titleEn;

        private BigDecimal gpsLat;
        private BigDecimal gpsLong;

        private Float popularity;
        private Float rating;

        private String nearestMetro;
        private String mainPhotoPath;

        private Integer category_1;
        private Integer category_2;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setTitleRu(String titleRu) {
            this.titleRu = titleRu;
            return this;
        }

        public Builder setTitleEn(String titleEn) {
            this.titleEn = titleEn;
            return this;
        }

        public Builder setGpsLat(BigDecimal gpsLat) {
            this.gpsLat = gpsLat;
            return this;
        }

        public Builder setGpsLong(BigDecimal gpsLong) {
            this.gpsLong = gpsLong;
            return this;
        }

        public Builder setPopularity(Float popularity) {
            this.popularity = popularity;
            return this;
        }

        public Builder setRating(Float rating) {
            this.rating = rating;
            return this;
        }

        public Builder setNearestMetro(String nearestMetro) {
            this.nearestMetro = nearestMetro;
            return this;
        }

        public Builder setMainPhotoPath(String mainPhotoPath) {
            this.mainPhotoPath = mainPhotoPath;
            return this;
        }

        public Builder setCategory_1(Integer category_1) {
            this.category_1 = category_1 == null ? 0 : category_1;
            return this;
        }

        public Builder setCategory_2(Integer category_2) {
            this.category_2 = category_2 == null ? 0 : category_2;
            return this;
        }

        public ShowplaceDTOForListsAllInOne build() {
            ShowplaceDTOForListsAllInOne showplaceDTO = new ShowplaceDTOForListsAllInOne();

            showplaceDTO.setId(this.id);
            showplaceDTO.setTitleEn(this.titleEn);
            showplaceDTO.setTitleRu(this.titleRu);
            showplaceDTO.setGpsLat(this.gpsLat);
            showplaceDTO.setGpsLong(this.gpsLong);
            showplaceDTO.setPopularity(this.popularity);
            showplaceDTO.setRating(this.rating);
            showplaceDTO.setNearestMetro(this.nearestMetro);
            showplaceDTO.setMainPhotoPath(this.mainPhotoPath);
            showplaceDTO.setCategory_1(this.category_1);
            showplaceDTO.setCategory_2(this.category_2);

            return showplaceDTO;
        }
    }

}
