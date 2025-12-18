package at.cgsit.train.anntation;

import java.lang.reflect.Method;

public class MainTest {

    // Annotationen sind Metadaten – kein Code
    // Logik kommt durch Reflection, AOP oder Frameworks
    // Retention entscheidet, ob man sie zur Laufzeit sieht
    static void main() throws NoSuchMethodException {

        Method method = MyClass.class.getMethod("login");

        if (method.isAnnotationPresent(Audit.class)) {
            Audit audit = method.getAnnotation(Audit.class);
            System.out.println(audit.value());
        } else
            System.out.println("audit nicht da für diese methode");

    }
}
