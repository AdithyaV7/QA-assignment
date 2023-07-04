package org.example;
//AS2019502
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/*
This Class for handle first page when we navigate to ebay.com
    1. Verify the Url
    2. Select Cell Phones & Accessories from dropdown
    3. Input "Mobile phone" in search box
    4. Submit search/ Click search button
 */
public class FirstPage extends Navigate {
    @Test(priority = 1)
    @Parameters({"url"}) //get url from XML file
    public void validateUrl(String url){
        String currentUrl = driver1.getCurrentUrl(); //Get current url
        Assert.assertEquals(currentUrl,url); //Validate Url (if true test pass)
    }

    @Test(priority = 2)
    public void categorySelection() throws InterruptedException {
        WebElement dropdown = driver1.findElement(By.id("gh-cat"));// Select dropdown by id
        dropdown.click(); //Click to see all category
        Select category = new Select(dropdown); // new selection object
        category.selectByVisibleText("Cell Phones & Accessories"); // category selection by visible text
        // below code -> not necessary
        Thread.sleep(1000); // wait for 1 second before close dropdown
        dropdown.click(); // close dropdown by selecting again
    }
    @Test(priority = 3)
    public void search() throws InterruptedException {
        // Select Search box by Xpath
        WebElement searchBox = driver1.findElement(By.xpath("//input[@id='gh-ac']"));
        searchBox.sendKeys("Mobile phone"); // Type Mobile phone in search bar
        // searchBox.sendKeys(Keys.ENTER); <- can use for submit the search (Press Enter)
        Thread.sleep(3000); //wait 3 seconds before click search
    }
    @Test(priority = 4)
    public void submitSearch(){
        //Select search button
        WebElement searchButton = driver1.findElement(By.xpath("//input[@id='gh-btn']"));
        searchButton.click(); //Click the search button
    }
}
