package com.epam;


import com.epam.steps.YandexSteps;
import com.epam.util.RetryAnalyzer;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class YandexTest {

    private YandexSteps yandexSteps;
    private static final Logger LOG = Logger.getLogger(YandexTest.class);


    @BeforeTest(description = "Init browser")
    public void setUp() {
        yandexSteps = new YandexSteps();
        yandexSteps.initBrowser();
        LOG.info("BeforeTest: start browser");
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void assertOneCanOpenMainPage () {
        LOG.info("start test method assertOneCanOpenMainPage");
        Assert.assertTrue(yandexSteps.isOpenPage());
    }

    @Test
    public void assertOneCanTakeCorrectSearchResult () {
        LOG.info("start test method assertOneCanTakeCorrectSearchResult");
        Assert.assertTrue(yandexSteps.isCorrectSearch());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void assertOneCanSingIn () {
        LOG.info("start test method assertOneCanSingIn");
        Assert.assertTrue(yandexSteps.isCorrectSingIn());
    }

    @AfterTest(description = "Stop Browser")
    public void stopBrowser() {
        yandexSteps.closeDriver();
        LOG.info("AfterTest: close browser");
    }

}
