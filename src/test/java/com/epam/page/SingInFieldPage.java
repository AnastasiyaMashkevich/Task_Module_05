package com.epam.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Block(@FindBy (xpath = "//div[@class = 'domik2__dropdown']"))
public class SingInFieldPage extends HtmlElement {

    @FindBy (xpath = "//input[@placeholder = 'Логин']")
        private WebElement fieldLogin;

    @FindBy (xpath = "//input[@placeholder = 'Пароль']")
        private WebElement fieldPassword;

    @FindBy (xpath = "//div[@class = 'domik2__submit']//button[@type = 'submit']")
        private WebElement submit;

     public void singIn (String login, String psw) {
         fieldLogin.sendKeys(login);
         fieldPassword.sendKeys(psw);
         submit.click();
     }


}
