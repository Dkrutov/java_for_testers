package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost",username);
        //создать адресс на почтовом сервере (JamesHelper)
        //заполняем форму слздания и отправляем (браузер)
        //ждем почту (MailHelper)
        //извлечь ссылку из письма
        //проходим по ссылке и завершаем регистрацию (браузер)
        //проверяем пользоватль может залогиниться (HttpSessionHelper)
    }
}
