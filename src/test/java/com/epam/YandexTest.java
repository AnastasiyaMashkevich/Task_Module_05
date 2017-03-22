package com.epam;


import com.epam.page.YandexMailPage;
import com.epam.page.YandexMainPage;
import com.epam.steps.YandexSteps;
import com.epam.util.RetryAnalyzer;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class YandexTest {
    //private ProxyServer bmp = new ProxyServer(9999);


    private YandexSteps yandexSteps;
    private static final Logger LOG = Logger.getLogger(YandexTest.class);


    @BeforeTest(description = "Init browser")
    public void setUp() throws Exception {

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


    @Test
    public void gettingHar() throws Exception {
        ProxyServer bmp = new ProxyServer(9999);
        //bmp.start();
        //yandexSteps.isOpenPage();
        bmp.newHar("www.yandex.by");
        yandexSteps.open();
        Har har = bmp.getHar();
        System.out.println(har.getLog().getBrowser().getName());
        System.out.println(har.getLog().getBrowser().getVersion());

        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println(request.getUrl() + " : " + response.getStatus()
                    + ", " + entry.getTime() + "ms");

            //assertThat(response.getStatus(), is(200));
        }
    }


    @AfterTest(description = "Stop Browser")
    public void stopBrowser() {
        yandexSteps.closeDriver();
        LOG.info("AfterTest: close browser");
    }

}
