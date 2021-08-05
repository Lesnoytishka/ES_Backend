package ea_and_st.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "showplace_type_tbl")
@Data
@NoArgsConstructor
public class ShowplaceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "showplace_type_id")
    private Integer id;

    @Column(name = "title_ru")
    private String titleRu;

    @Column(name = "title_en")
    private String titleEn;
}
