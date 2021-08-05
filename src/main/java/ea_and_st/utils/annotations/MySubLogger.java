package ea_and_st.utils.annotations;

import com.ea_and_st.utils.enums.ApiSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks dependent methods for registration
 * @see com.ea_and_st.utils.aspects.LogAspect
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MySubLogger {
    ApiSource source();
}
