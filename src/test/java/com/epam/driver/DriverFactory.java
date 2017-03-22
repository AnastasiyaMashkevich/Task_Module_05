package com.epam.driver;



import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * Created by anastasiya_mashkevic on 3/20/17.
 */
public class DriverFactory {
    private static WebDriver driver;

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_CHROME = "webdriver.chrome.driver";
    private static final String GECKODRIVER_EXE_PATH = "./src/test/resources/geckodriver.exe";
    private static final String CHROME_DRIVER = "chromedriver"; //src/test/resources/

    private DriverFactory () {}

    public static  WebDriver getDriver(String browser) throws Exception { // add synchronized
        if (driver == null) {
            {
                switch (browser) {
                    case "chrome": {
                        synchronized (DriverFactory.class) {
                            if (driver == null) {
                                ProxyServer bmp = new ProxyServer(9999);
                                bmp.start();
                                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                                capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
                                driver = new ChromeDriver(capabilities);
                                System.setProperty(WEBDRIVER_CHROME, CHROME_DRIVER);
                                driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
                                driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
                                driver.manage().window().maximize();
                            }
                        }
                    }
                    case "firefox": {
                        synchronized (DriverFactory.class) {
                            if (driver == null) {
                                System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
                                driver = new FirefoxDriver();
                                driver.manage().timeouts().pageLoadTimeout(35, TimeUnit.SECONDS);
                                driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
                                driver.manage().window().maximize();
                            }
                        }
                    }
                }
            }
        }

        return driver;
    }

}
