package ea_and_st.utils;

import com.ea_and_st.entities.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class DefaultUserFactory {

    public static User getDefaultUser(String name, String email) {
        User user = new User();
        user.setGender('M');
        user.setBirthYear(1990);
        user.setEmail(email);
        user.setName(name);
        user.setIsDeleted(false);
        user.setRegDate(new Date());
        user.setLanguage("ru");
        user.setLocationType("CITIZEN");
        user.setId(new Date().getTime());
        return user;
    }
}
