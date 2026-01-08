package at.cgsit.train.anntation;

@Demo
public class MyClass {

    @Demo
    public void test() {
        System.out.println("diese methode wurde aufgerufen!!!");
    }

    @Audit(value = "MY_USER_LOGIN_ANNNOT_Value", enabled = true)
    public void login() {
    }

    private void secretLogin() {
        System.out.println("Private Methode aufgerufen");
    }

}
