package com.epam.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Block;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@Block(@FindBy(xpath = "//*[@class ='home-arrow__search']/form"))
public class SearchFieldPage extends HtmlElement {

    @FindBy(xpath = "*//input[@id ='text']")
     private WebElement requestInput;

    @FindBy(xpath = "//div[@class = 'search2__button']/button[@type='submit']")
    private WebElement searchButton;

    public void search(String query) {
        requestInput.sendKeys(query);
        searchButton.click();
    }
}
