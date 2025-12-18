package at.cgsit.train.anntation;

public class MyClass {

    @Demo
    public void test() {
    }

    @Audit(value = "MY_USER_LOGIN_ANNNOT_Value", enabled = true)
    public void login() {
    }


}
