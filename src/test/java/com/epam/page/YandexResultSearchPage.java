package com.epam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 * Created by anastasiya_mashkevic on 3/20/17.
 */
public class YandexResultSearchPage extends AbstractPage{

    public YandexResultSearchPage(WebDriver driver) {
        super(driver);
        HtmlElementLoader.populatePageObject(this, this.driver);
    }

    @FindBy (xpath = "*//li[1][contains (@class,'serp-item')]")
    private WebElement result;


    public boolean assertResultSearch ()
    {
        return result.getText().contains(StaticParamClass.QUERY);
    }

    public void openPage() {
        driver.navigate().to(StaticParamClass.BASE_URL);
    }
}
