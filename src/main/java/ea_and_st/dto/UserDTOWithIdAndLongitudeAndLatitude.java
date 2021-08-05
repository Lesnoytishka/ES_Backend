package ea_and_st.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserDTOWithIdAndLongitudeAndLatitude {
    Long userId;
    BigDecimal longitude;
    BigDecimal latitude;
}
