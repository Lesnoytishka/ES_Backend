package ea_and_st.utils.enums;

import lombok.Getter;

@Getter
public enum ColorOfWorkload {
    ZERO_DATA(0),
    MINIMAL_LOAD(1),
    MEDIUM_LOAD(2),
    HIGHEST_LOAD(3);

    private int value;
    ColorOfWorkload(int value) {
        this.value = value;
    }
}
