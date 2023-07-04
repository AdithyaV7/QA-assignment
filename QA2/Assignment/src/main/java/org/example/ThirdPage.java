package org.example;
//AS2019502
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

/*
This class handled Newly opened 1st Item page
    1.Select dropdown Model,Carrier,Storage,Color,Cosmetic and get * 1st * item selected
        use try,catch for prevent process failure if above dropdown not existed
            use sleep for wait 2.5 second for enter choices(automatically)

    2. We can use selectboxlabel to find item by its labeled name but there are different no. of dropdown boxes
        So we can use there id  x-msku__select-box-1005  to select last drop down options
        and 'selectboxid = 1000' - 'selectboxid = 1004' to get first 5 dropdown options
        Selecting by Id is more suitable than label because we select 1st item available in every drop down

    3. Class names are renamed as selectA to selectF to give alphabetic order to tests <- Unfortunately test -
        - priority does not work properly
 */

public class ThirdPage extends Navigate{
    @Test(priority = 7)
    public void selectA() throws InterruptedException {
        try {
            //WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxlabel = 'Model']"));
            WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxid ='1000']")); //Select 1st dropdown box
            //dropbox.click();
            Select model = new Select(dropbox);
            model.selectByIndex(1);
        }catch (Exception e){
            System.out.println("Not any dropdown boxes exist in this page"); // If there are no dropdown box exists in that page
        }
        Thread.sleep(2500);
    }
    @Test(priority = 8)
    public void selectB() throws InterruptedException {
        try {
            //WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxlabel = 'Carrier']"));
            WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxid ='1001']")); //Select 2nd dropdown box
            Select model = new Select(dropbox);
            model.selectByIndex(1);
        }catch (Exception e){
            System.out.println("2nd dropdown DNE");
        }
        Thread.sleep(2500);
    }
    @Test(priority = 9)
    public void selectC() throws InterruptedException {
        try {
            //WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxlabel = 'Storage Capacity'] "));
            WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxid ='1002']")); //Select 3rd dropdown box
            Select model = new Select(dropbox);
            model.selectByIndex(1);
        }catch (Exception e){
            //System.out.println("Model not found");
        }
        Thread.sleep(2500);
    }
    @Test(priority = 9)
    public void selectD() throws InterruptedException {
        try {
            //WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxlabel = 'Memory'] "));
            WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxid ='1003']")); //Select 4th dropdown box
            Select model = new Select(dropbox);
            model.selectByIndex(1);
        }catch (Exception e){
            //System.out.println("Model not found");
        }
        Thread.sleep(2500);
    }
    @Test(priority = 10)
    public void selectE() throws InterruptedException {
        try {
            //Color and Colour both can find in webs -> therefore use 'start-with' for solve it
            //WebElement dropbox = driver1.findElement(By.xpath("//select[starts-with(@selectboxlabel,'Colo')]"));
            WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxid ='1004']")); //Select 5th dropdown box
            Select model = new Select(dropbox);
            model.selectByIndex(1);
        }catch (Exception e){
            //System.out.println("Model not found");
        }
        Thread.sleep(2500);
    }

    @Test(priority = 11)
    public void selectF() throws InterruptedException {
        try {
            //WebElement dropbox = driver1.findElement(By.cssSelector("select[selectboxlabel = 'Cosmetic']")); //select Cosmetic option
            WebElement dropbox = driver1.findElement(By.cssSelector("select#x-msku__select-box-1005")); ////Select 6th dropdown box
            Select model = new Select(dropbox);
            model.selectByIndex(1); //Select 1st item
        }catch (Exception e){
            //System.out.println("Model not found");
        }
        Thread.sleep(2500);
    }

}
