package org.example;
//AS2019502-Adithya
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.lang.reflect.Member;
import java.time.Duration;

/*
This Class for,
    1. Load the Drivers
        Parallel testing is enabled but firefox driver runs 1 minute after the Chrome browser
        otherwise only last opened browser completed the task
        *** ONLY CHROME TEST IS WORKING -> FIREFOX XML IS Disabled. ("CTRL" + "/")
    2. Navigate to URL
    3. After the test -> Close the drive
 */
public class Navigate {
    public static WebDriver driver1;
    @BeforeTest
    @Parameters({"browser"})
    public void settings(String browser) throws InterruptedException {
        /*
        Chrome driver 111 has a issue with selenium webdriver
        so we need to give web socket permissions manually(I think!) -> give access to all origins
            1. Create new Chrome option Obj
            2. Pass the parameters to created Obj
            3. Pass the Obj to ChromeDriver()
         */
        ChromeOptions server = new ChromeOptions();
        server.addArguments("--remote-allow-origins=*");
        if(browser.equalsIgnoreCase("chrome")){
            String chroDriverPath = "drivers/chromedriver.exe"; // Chrome driver Path
            System.setProperty("webdriver.chrome.driver",chroDriverPath);
            WebDriverManager.chromedriver().setup();
            driver1 = new ChromeDriver(server);
        } else if (browser.equalsIgnoreCase("firefox")) {
            Thread.sleep(50000); // <- tried to wait for one test for one browser completed and run 2nd one
            WebDriverManager.firefoxdriver().setup();
            driver1 = new FirefoxDriver();
        }else{
            System.out.println("Browser Does Not Support !!");
        }
        driver1.manage().window().maximize();// Maximize the Web window
        //driver1.manage().deleteAllCookies(); // Clear cookies (because website auto load with previous selections
        WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    @Parameters({"url"})
    public void navigateUrl(String url){
        driver1.get(url); //Load the Url to driver
    }

    @AfterTest
    public void closeDriver() throws InterruptedException {
        Thread.sleep(10000); //Give 10 seconds before close the browser
        driver1.quit(); // Quit the browser
    }

}