package ea_and_st.dto;

import com.ea_and_st.entities.ShowplaceActiveTime;
import com.ea_and_st.utils.configs.MediaServerConfig;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShowplaceDTOAboutInfo {

    private Long id;

    private String titleRu;
    private String titleEn;

    private String address;
    private BigDecimal gpsLat;
    private BigDecimal gpsLong;

    private Float popularity;
    private Float rating;

    private String photoUri;

    private String infoText;
    private String descriptionEn;

    private Integer category1;
    private Integer category2;

    private String price;
    private String reducedPrice;

    private String nearestMetro;

    private String[] priceGroups;
    private Integer[] priceCost;

    private String[] workTime;
    private int[] colorId;

    private boolean favorite;

    @RequiredArgsConstructor
    public static class Builder {
        private final MediaServerConfig mediaServerConfig = new MediaServerConfig();
        private Long id;
        private String titleRu;
        private String titleEn;
        private String address;
        private BigDecimal gpsLat;
        private BigDecimal gpsLong;
        private Float popularity;
        private Float rating;
        private String mainPhotoPath;
        private String infoText;
        private String descriptionEn;
        private Integer category1;
        private Integer category2;

        private String[] priceGroups;

        private String[] workTime;
        private int[] colorId;

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

        public Builder setAddress(String address) {
            this.address = address;
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

        public Builder setMainPhotoPath(String mainPhotoPath) {
            this.mainPhotoPath = mainPhotoPath;
            return this;
        }

        public Builder setDescriptionRu(String descriptionRu) {
            this.infoText = descriptionRu;
            return this;
        }

        public Builder setDescriptionEn(String descriptionEn) {
            this.descriptionEn = descriptionEn;
            return this;
        }

        public Builder setActiveTimes(List<ShowplaceActiveTime> activeTimes) {
//            this.activeTimes = activeTimes;
            return this;
        }

        public Builder setWorkTime(String[] workTime) {
            this.workTime = workTime;
            return this;
        }

        public Builder setColorId(int[] colorId) {
            this.colorId = colorId;
            return this;
        }

        public Builder setCategory1(Integer category1) {
            this.category1 = category1;
            return this;
        }

        public Builder setCategory2(Integer category2) {
            this.category2 = category2;
            return this;
        }

        public Builder setPriceGroups(String[] priceGroups) {
            this.priceGroups = priceGroups;
            return this;
        }

        public ShowplaceDTOAboutInfo build() {
            ShowplaceDTOAboutInfo info = new ShowplaceDTOAboutInfo();
            info.setId(this.id);
            info.setTitleRu(this.titleRu);
            info.setTitleEn(this.titleEn);
            info.setAddress(this.address);
            info.setGpsLat(this.gpsLat);
            info.setGpsLong(this.gpsLong);
            info.setPopularity(this.popularity);
            info.setRating(this.rating);
            info.setPhotoUri(mediaServerConfig.getPath() + this.mainPhotoPath);
            info.setInfoText(this.infoText);
            info.setDescriptionEn(this.descriptionEn);
            info.setWorkTime(this.workTime);
            info.setColorId(this.colorId);
            info.setCategory1(this.category1 != null ? this.category1 : 0);
            info.setCategory2(this.category2 != null ? this.category2 : 0);
            info.setPriceGroups(this.priceGroups);
            return info;
        }

        public ShowplaceDTOAboutInfo build(ShowplaceDTOAboutInfoMainPart part) {
            ShowplaceDTOAboutInfo info = new ShowplaceDTOAboutInfo();
            info.setId(part.getShowplaceId());
            info.setTitleRu(part.getTitleRu());
            info.setTitleEn(part.getTitleEn());
            info.setAddress(part.getAddress());
            info.setGpsLat(part.getGpsLat());
            info.setGpsLong(part.getGpsLong());
            info.setPopularity(part.getPopularity());
            info.setRating(part.getRating());
            info.setPhotoUri(mediaServerConfig.getPath() + part.getMainPhotoPath());
            info.setInfoText(part.getDescriptionRu());
            info.setDescriptionEn(part.getDescriptionEn());

            info.setPrice(part.getPrice());
            info.setReducedPrice(part.getReducedPrice());

            info.setNearestMetro(part.getNearestMetro());

            info.setPriceGroups(stringSplicerToStringArr(part.getPriceGroups()));
            info.setPriceCost(stringSplicerToIntegerArr(part.getPriceCost()));

            info.setCategory1(part.getCategoryOfInterests1() != null ? part.getCategoryOfInterests1() : 0);
            info.setCategory2(part.getCategoryOfInterests2() != null ? part.getCategoryOfInterests2() : 0);

            info.setWorkTime(this.workTime);
            info.setColorId(this.colorId);
            return info;
        }

        private String[] stringSplicerToStringArr(String priceGroups) {
            String[] strings = priceGroups.split(",");
            String[] result = new String[strings.length];
            StringBuilder sb;
            for (int i = 0; i < strings.length; i++) {
                sb = new StringBuilder();
                for (char ch : strings[i].toCharArray()) {
                    if (!(ch == '\"' || ch == '{' || ch == '}')) sb.append(ch);
                }
                result[i] = sb.toString();
            }
            return result;
        }

        private Integer[] stringSplicerToIntegerArr(String priceCost) {
            String[] strings = priceCost.split(",");
            Integer[] result = new Integer[strings.length];
            StringBuilder sb;
            for (int i = 0; i < strings.length; i++) {
                sb = new StringBuilder();
                for (char ch : strings[i].toCharArray()) {
                    if (!(ch == '\"' || ch == '{' || ch == '}')) sb.append(ch);
                }
                result[i] = Integer.parseInt(sb.toString());
            }
            return result;
        }
    }
}

