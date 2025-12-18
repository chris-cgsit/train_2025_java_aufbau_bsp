package at.cgsit.train.anntation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainTest {

    // Annotationen sind Metadaten – kein Code
    // Logik kommt durch Reflection, AOP oder Frameworks
    // Retention entscheidet, ob man sie zur Laufzeit sieht
    static void main() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        // das hier ist eigentlich im Normalbetrieb VERBOTEN !!
        // nur für Framework-Entwicklung nutzen !!
        Method privateMethode = MyClass.class.getDeclaredMethod("secretLogin");
        privateMethode.setAccessible(true);

        MyClass instance = new MyClass();
        privateMethode.invoke(instance);


        // liefert alle PUBLIC Methoden
        Method[] methods = MyClass.class.getMethods();
        for (Method m : methods) {
            if ("test".equals(m.getName())) {
                // ja ich habe die methode die ich suche gefunden
            }
            System.out.println(m.getName());
        }


        // ich suche eine methode in meiner classe via metadaten
        Method methodTest = MyClass.class.getMethod("test");

        // wenn ich ein objekt habe. also eine instanz von dieser klasse
        MyClass myClassInstance = new MyClass();

        // kann ich mit dieser metadaten funktion invoke diese mehtode auch aufrufen.
        methodTest.invoke(myClassInstance);

        // wenn die methode parameter hat, kann man diese auch hier generisch übergeben.
        // und auch die rückgabewerte auslesen
        // Object result = method.invoke(instance, 3, 5);

        // wenn ich eine methode habe kann ich auch die Annotation die darauf codiert sind
        // auslesen, und die werte daraus in meinem Program Code verwenden.
        Method method = MyClass.class.getMethod("login");
        if (method.isAnnotationPresent(Audit.class)) {

            Audit audit = method.getAnnotation(Audit.class);
            String value = audit.value();
            System.out.println(value);
//
//            RoleSystem.getRoleInfo(value);
//            User myUser;
//            RoleSystem.isUserInRole(mUser, value);

        } else
            System.out.println("audit nicht da für diese methode");

    }
}
