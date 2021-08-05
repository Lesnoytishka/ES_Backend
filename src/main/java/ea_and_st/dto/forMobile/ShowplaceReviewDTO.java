package ea_and_st.dto.forMobile;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ShowplaceReviewDTO {
    private Long reviewId;
    private String userPhotoUri;
    private String userName;
    private int rating;
    private String content;
    private Date addDate;
}
