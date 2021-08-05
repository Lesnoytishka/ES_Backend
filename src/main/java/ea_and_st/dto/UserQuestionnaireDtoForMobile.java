package ea_and_st.dto;

import com.ea_and_st.entities.CategoryOfInterests;
import lombok.Data;

import java.util.List;

@Data
public class UserQuestionnaireDtoForMobile {
    private Long userId;
    private String gender;
    private String name;
    private Integer birthYear;
    private String locationType;
    private List<CategoryOfInterests> categoryOfInterests;
    private List<Integer> interests;
}
