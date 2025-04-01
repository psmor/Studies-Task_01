package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)         // Аннотация метода.
@Retention(RetentionPolicy.RUNTIME) // В процессе работы приложения.
public @interface Test {
    int priority() default  5;
}
