package ea_and_st.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "showplace_active_time_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowplaceActiveTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "showplace_active_time_id")
    private Long id;

    @Column(name = "showplace_id")
    private Long showplaceId;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "hours0")
    private int at0;

    @Column(name = "hours1")
    private int at1;

    @Column(name = "hours2")
    private int at2;

    @Column(name = "hours3")
    private int at3;

    @Column(name = "hours4")
    private int at4;

    @Column(name = "hours5")
    private int at5;

    @Column(name = "hours6")
    private int at6;

    @Column(name = "hours7")
    private int at7;

    @Column(name = "hours8")
    private int at8;

    @Column(name = "hours9")
    private int at9;

    @Column(name = "hours10")
    private int at10;

    @Column(name = "hours11")
    private int at11;

    @Column(name = "hours12")
    private int at12;

    @Column(name = "hours13")
    private int at13;

    @Column(name = "hours14")
    private int at14;

    @Column(name = "hours15")
    private int at15;

    @Column(name = "hours16")
    private int at16;

    @Column(name = "hours17")
    private int at17;

    @Column(name = "hours18")
    private int at18;

    @Column(name = "hours19")
    private int at19;

    @Column(name = "hours20")
    private int at20;

    @Column(name = "hours21")
    private int at21;

    @Column(name = "hours22")
    private int at22;

    @Column(name = "hours23")
    private int at23;

    @Column(name = "recommend_visit_time")
    private String visitTime;

    @Column(name = "comments")
    private String comments;

    @Column(name = "workload")
    private String workload;

}
