package pl.masuhr.pg.jpo.test.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({
        java.lang.annotation.ElementType.METHOD
})
public @interface Repeat {
    int value() default 1;
}