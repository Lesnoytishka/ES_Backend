package ea_and_st.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorite_showplace_tbl")
@Data
@NoArgsConstructor
public class FavoriteShowplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_showplace_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "showplace_id")
    private Long showplaceId;

    @Column(name = "add_time")
    @CreationTimestamp
    private Date addTime;

    @Column(name = "delete_time")
    private Date deleteTime;

    @Column(name = "is_deleted")
    private boolean deleted;
}
