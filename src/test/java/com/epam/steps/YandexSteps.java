package com.epam.steps;

import com.epam.driver.DriverFactory;
import com.epam.page.YandexMailPage;
import com.epam.page.YandexMainPage;
import com.epam.page.YandexResultSearchPage;
import com.epam.util.Config;
import org.openqa.selenium.WebDriver;

/**
 * Created by anastasiya_mashkevic on 3/20/17.
 */
public class YandexSteps {

    private WebDriver driver;


    public void initBrowser() throws Exception {
        driver = DriverFactory.getDriver("chrome");
    }

    public void closeDriver()
    {
        driver.quit();
    }

    public boolean isOpenPage ()
    {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.openPage();
        return yandexMainPage.isNeedPageOpen();
    }

    public  void open ( ){
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.openPage();
    }

    public boolean isCorrectSearch ()
    {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        YandexResultSearchPage yandexResultSearchPage = new YandexResultSearchPage(driver);
        yandexMainPage.openPage();
        yandexMainPage.search();
        return yandexResultSearchPage.assertResultSearch();
    }

    public boolean isCorrectSingIn ()
    {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        YandexMailPage yandexMailPage = new YandexMailPage(driver);
        yandexMainPage.openPage();
        yandexMainPage.singInYandex();
        return yandexMailPage.singInIsSuccess();
    }
}
