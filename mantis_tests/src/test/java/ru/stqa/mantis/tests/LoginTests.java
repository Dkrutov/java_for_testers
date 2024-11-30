package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        app.http().login("odlt","password");
        Assertions.assertTrue(app.http().isLogin());

    }

    @Test
    void canLoginSession() {
        app.session().login("odlt","password");
        Assertions.assertTrue(app.session().isLogin());

    }

}
