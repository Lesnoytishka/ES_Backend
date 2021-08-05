package ea_and_st.utils;

import com.ea_and_st.entities.ShowplaceReview;
import com.ea_and_st.entities.User;

import java.util.Date;

public class DefaultReviewFactory {
    public static ShowplaceReview getDefaultReview(Long showplaceId, String content, User user) {
        ShowplaceReview review = new ShowplaceReview();
        review.setShowplaceId(showplaceId);
        review.setActive(true);
        review.setAddDate(new Date());
        review.setUserId(user.getId());
        review.setReview(5);
        review.setContent(content);
        return review;
    }
}
