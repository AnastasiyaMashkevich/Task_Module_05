package com.epam;

import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by anastasiya_mashkevic on 3/21/17.
 */
public class YandexTestWithProxy {
    private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER = "chromedriver"; //src/test/resources/
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_EXE_PATH = "geckodriver";




    @Test
    public void testWithProxy() throws Exception {

        ProxyServer server = new ProxyServer(9999);
        Proxy proxy = server.seleniumProxy();
        server.start();


      DesiredCapabilities caps = DesiredCapabilities.chrome();

       // caps.setCapability(CapabilityType.PROXY, proxy);
        ArrayList<String> switches = new ArrayList<String>();
        switches.add("--proxy-server=localhost:8080");
        caps.setCapability("chrome.switches", switches);
      caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
        WebDriver driver = new ChromeDriver(caps);


//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--proxy-server=localhost:"  + server.getPort());
//
//        WebDriver driver = new ChromeDriver(option);

//        String PROXY = "localhost:8080";
//
//        ProxyServer server = new ProxyServer(8080);
//        server.start();
//
//        Proxy proxy = server.seleniumProxy();
//        proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(CapabilityType.PROXY, proxy);
//        System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
//        WebDriver driver = new FirefoxDriver(capabilities);



        server.newHar("yandex.ru");

        driver.get("http://yandex.ru");
        Thread.sleep(15000);
        Har har = server.getHar();


        System.out.println(har.getLog().getBrowser().getName());
        System.out.println(har.getLog().getBrowser().getVersion());

        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();

            System.out.println(request.getUrl() + " : " + response.getStatus()
                    + ", " + entry.getTime() + "ms");


        }
    }
}




