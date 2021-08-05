package ea_and_st.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "showplace_review_tbl")
@Data
@NoArgsConstructor
public class ShowplaceReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showplace_review_id")
    private Long id;

    @Column(name = "showplace_id")
    private Long showplaceId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "review")
    private Integer review;

    @Column(name = "content")
    private String content;

    @Column(name = "is_active")
    private boolean active;
}
