package lk.ijse.cmjd109.classprojectlibrarymanagementsoftware.anotations;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Service
@Transactional
public @interface TransactionalService {
    String value() default "";
}
