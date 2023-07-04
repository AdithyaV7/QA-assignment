package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/*
This Class include,
    1. Get checkBox and select Apple brand
    2. After the refresh select first Item
    3. Navigate to new tab
 */

public class SecondPage extends Navigate {

    //Select Apple brand from check box
    @Test(priority = 5)
    public void checkbox() throws InterruptedException {
        WebElement appleSelect = driver1.findElement(By.xpath("//body/div[8]/div[3]/ul[1]/li[1]/ul[1]/li[2]/ul[1]/li[1]/div[2]/ul[1]/li[1]/div[1]/a[1]/div[1]/span[1]"));
        //Unexpectecdly above Xpath is long
        appleSelect.click(); //Click checkBox
        Thread.sleep(4000); //wait 4 seconds
    }
    // Click 1st Apple item from search results
    @Test(priority = 6)
    public void selectFirst(){
        WebElement firstItm = driver1.findElement(By.xpath("//*[@id=\"srp-river-results\"]/ul/li[2]/div/div[2]/a/div"));
        firstItm.click(); //Click

        // go to the newly opened tab URL
        for (String handle : driver1.getWindowHandles()) {
            driver1.switchTo().window(handle);
        }

    }
}
