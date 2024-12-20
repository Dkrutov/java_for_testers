package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunction;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.UserData;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    DeveloperMailUser user;

//    @Test
//    void canRegisterUserJamesCli() {
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
//        app.session().registrationStepTwo(username,"password");
//        //проверяем пользователь может залогиниться (HttpSessionHelper)
//        app.http().login(username,"password");
//        Assertions.assertTrue(app.http().isLogin());
//    }

    @Test
    void canRegisterUserDeveloperMail() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

        app.session().registrationStepOne(user.name(), email);

        var message = app.developerMail().receive(user, Duration.ofSeconds(60));

        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(message);
        if (matcher.find()) {
            var url = message.substring(matcher.start(), matcher.end());
            app.driver().get(url);
        }

        app.session().registrationStepTwo(user.name(), password);

        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLogin());
    }

//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }

    @Test
    void canRegisterUserJamesApi() {
        var password = "password";
        var userName = CommonFunction.randomString(4);
        var email = String.format("%s@localhost", userName);
        var realName = CommonFunction.randomString(8);

        app.jamesApi().addUser(email, password);
        app.rest().createUser(new UserData()
                .withPassword(password)
                .withRealName(realName)
                .withUserName(userName)
                .withEmail(email)
        );

        var messages = app.mail().receive(email, password, Duration.ofSeconds(60));
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        if (matcher.find()) {
            var url = text.substring(matcher.start(), matcher.end());
            app.driver().get(url);
        }
        app.session().registrationStepTwo(realName, password);
        app.http().login(userName, password);
        Assertions.assertTrue(app.http().isLogin());
    }

//    @Test
//    void canCreateUserApi() {
//        var userName = CommonFunction.randomString(8);
//        app.rest().createUser(new UserData()
//                .withPassword("password")
//                .withRealName(CommonFunction.randomString(8))
//                .withUserName(userName)
//                .withEmail(String.format("%s@localhost", userName))
//        );
//    }
}