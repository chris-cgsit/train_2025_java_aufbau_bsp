package at.cgsit.train.anntation;

import java.lang.annotation.*;

/**
 * runtime retnetion bedeutet, dass die anntation auch während der programm ausführung
 * also zur laufzeit verfügbar ist . und wir können darauf zugreifen
 * <br>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Demo {
}
