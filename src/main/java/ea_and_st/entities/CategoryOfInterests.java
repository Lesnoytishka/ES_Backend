package ea_and_st.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "category_of_interest_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryOfInterests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_of_interest_id")
    private Integer id;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "title_en")
    private String titleEn;
}
