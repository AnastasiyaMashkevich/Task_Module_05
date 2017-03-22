package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

import java.util.concurrent.TimeUnit;

/**
 * Created by anastasiya_mashkevic on 3/20/17.
 */
public class YandexMailPage extends AbstractPage{

    @FindBy (xpath = "//div[@class = 'mail-User-Name']")
    private WebElement userMail;

    public YandexMailPage (WebDriver driver) {
        super(driver);
        HtmlElementLoader.populatePageObject(this, this.driver);
    }

    public YandexMailPage () {}

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public boolean singInIsSuccess () {
        return userMail.getText().equals(StaticParamClass.LOGIN);
    }
}
