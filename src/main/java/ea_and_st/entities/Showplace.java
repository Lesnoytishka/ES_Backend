package ea_and_st.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "showplace_tbl")
@Data
@NoArgsConstructor
public class Showplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showplace_id")
    private Long showplaceId;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "title_lower_ru")
    private String titleLowerRu;

    @Column(name = "title_en")
    private String titleEn;

    @ManyToOne
    @JoinColumn(name = "showplace_type_id")
    private ShowplaceType showplaceType;

    @Column(name = "url")
    private String url;

    @Column(name = "address")
    private String address;

    @Column(name = "gps_lat")
    private BigDecimal gpsLat;

    @Column(name = "gps_long")
    private BigDecimal gpsLong;

    @Column(name = "popularity")
    private BigDecimal popularity;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "description_ru")
    private String descriptionRu;

    @Column(name = "description_en")
    private String descriptionEn;

    @Column(name = "main_photo")
    private String mainPhotoPath;

    @Column(name = "other_photo")
    private String otherPhoto;

    @Column(name = "k1")
    private Integer categoryOfInterests1;

    @Column(name = "k2")
    private Integer categoryOfInterests2;

    @Column(name = "k3")
    private Integer categoryOfInterests3;

    @Column(name = "k4")
    private Integer categoryOfInterests4;

    @Column(name = "k5")
    private Integer categoryOfInterests5;

    @Column(name = "is_open")
    private Boolean open;

    @Column(name = "nearest_metro")
    private String nearestMetro;

    @Column(name = "audio_guide")
    private Boolean audioGuide;

    @Column(name = "audio_guide_price")
    private BigDecimal audionGuidePrice;

    @Column(name = "individual_excursions")
    private Boolean individualExcursions;

    @Column(name = "interactions")
    private String interactions;

    @Column(name = "price")
    private String price;

    @Column(name = "reduced_price")
    private String reducedPrice;


    @Column(name = "price_groups")
    private String priceGroups;

    @Column(name = "price_cost")
    private String priceCost;

    @Column(name = "coronavirus_description")
    private String coronavirusDescription;

    @Column(name = "release_version")
    private Integer releaseVersion;


}
