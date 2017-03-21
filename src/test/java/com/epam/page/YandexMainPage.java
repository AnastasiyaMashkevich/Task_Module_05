package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;


public class YandexMainPage extends AbstractPage {

    private SearchFieldPage searchFieldPage = new SearchFieldPage();
    private SingInFieldPage singInFieldPage = new SingInFieldPage();


    @FindBy(xpath = "*//div[@aria-label = 'Яндекс']")
    private WebElement logo;

    public YandexMainPage(WebDriver driver) {
        super(driver);
        HtmlElementLoader.populatePageObject(this, this.driver);
    }

    public void openPage () {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }

    public void search() {
        searchFieldPage.search(StaticParamClass.QUERY);
    }

    public void singInYandex () {
        singInFieldPage.singIn(StaticParamClass.LOGIN,StaticParamClass.PASSWORD);
    }

    public boolean isNeedPageOpen ()
    {
        return logo.isDisplayed();

    }


}
