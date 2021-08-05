package ea_and_st.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user_tbl")
@Data
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "lang")
    private String language;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "token")
    private String token;

    @ManyToMany
    @JoinTable(
            name = "user_categories_of_interests_tbl",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "category_of_interest_id")
    )
    private List<CategoryOfInterests> categoryOfInterests;

    @Column(name = "reg_date")
    @CreationTimestamp
    private Date regDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "photo_path")
    private String photo;

    @Column(name = "location_type")
    private String locationType;

    @Column(name = "gps_lat")
    private BigDecimal gpsLat;

    @Column(name = "gps_long")
    private BigDecimal gpsLong;

    @Column(name = "version_favorites")
    private Long versionFavorites;

    @Column(name = "version_lists")
    private Long versionLists;
}
