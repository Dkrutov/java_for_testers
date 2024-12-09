package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.model.DeveloperMailUser;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

//    @Test
//    void canRegisterUser() {
//        var username = CommonFunction.randomString(4);
//        var email = String.format("%s@localhost",username);
//        //создать адресс на почтовом сервере (JamesHelper)
//        app.jamesApi().addUser(email,"password");
//        //заполняем форму создания и отправляем (браузер)
//        app.session().registrationStepOne(username,email);
//        //ждем почту (MailHelper)
//        var messages  = app.mail().receive(email,"password", Duration.ofSeconds(60));
//        //извлечь ссылку из письма
//        var text = messages.get(0).content();
//        var pattern = Pattern.compile("http://\\S*");
//        var matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            var url = text.substring(matcher.start(), matcher.end());
//            app.driver().get(url);
//        }
//        //проходим по ссылке и завершаем регистрацию (браузер)
//        app.session().registrationStepTwo(username);
//        //проверяем пользователь может залогиниться (HttpSessionHelper)
//        app.http().login(username,"password");
//        Assertions.assertTrue(app.http().isLogin());
//    }

    @Test
    void canRegisterUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@localhost",user.name());

//        app.session().registrationStepOne(username,email);
//        var messages  = app.mail().receive(email,password, Duration.ofSeconds(60));
//        var text = messages.get(0).content();
//        var pattern = Pattern.compile("http://\\S*");
//        var matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            var url = text.substring(matcher.start(), matcher.end());
//            app.driver().get(url);
//        }
//        app.session().registrationStepTwo(username);
//        app.http().login(username,password);
//        Assertions.assertTrue(app.http().isLogin());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }

}
