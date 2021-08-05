package ea_and_st.utils.aspects;

import com.ea_and_st.utils.annotations.MySubLogger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private long timeStart;

    @Pointcut("@annotation(com.ea_and_st.utils.annotations.MyLogger)")
    public void selectLoggerMethodsAvailable() {
    }

    @Pointcut("@annotation(com.ea_and_st.utils.annotations.MySubLogger)")
    public void selectSubLoggerMethodAvailable() {
    }

    @Before("selectLoggerMethodsAvailable()")
    public void beforeAdvice() {
        timeStart = System.currentTimeMillis();
    }

    @After("selectLoggerMethodsAvailable()")
    public void afterAdvice(JoinPoint jp) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(jp.getSignature().getName()).append("(");
        for (Object arg : jp.getArgs()) {
            stringBuilder.append(arg).append(", ");
        }
        stringBuilder
                .replace(stringBuilder.length() - 2, stringBuilder.length(), ")")
                .append(getLostTime(timeStart, System.currentTimeMillis()))
        ;
        log.info(stringBuilder.toString());
    }

    @After("selectSubLoggerMethodAvailable()")
    public void afterSubAdvice(JoinPoint joinPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("  |-(")
                .append(getSourceKey(joinPoint))
                .append(")- ")
                .append(joinPoint.getSignature().getName()).append("(");
        for (Object arg : joinPoint.getArgs()) {
            stringBuilder.append(arg).append(", ");
        }
        stringBuilder
                .replace(stringBuilder.length() - 2, stringBuilder.length(), ")")
                .append(getLostTime(timeStart, System.currentTimeMillis()))
        ;
        log.info(stringBuilder.toString());
    }

    private String getSourceKey(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        MySubLogger source = method.getAnnotation(MySubLogger.class);
        String sourceKey;
        switch (source.source()){
            case ANALYTICAL:
                sourceKey = "a";
                break;
            case JAVA_DATABASE:
                sourceKey = "d";
                break;
            default:
                sourceKey = "-";
        }
        return sourceKey;
    }

    private static String getLostTime(long timeStart, long timeEnd) {
        long difference = timeEnd - timeStart;
        long sec = difference / 1000;
        long min = sec / 60;
        return String.format(" Time lost: %dm %ds %dms", min, sec, difference % 1000);
    }
}
