package org.example;
//AS2019502
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
/*
This Class use for get items name from choices selection page and verify it with cart page
    1. item Name, Price, Condition, Quantity stored as private variables
    2. Get and store Item info as string
    3. Print Item Name, Price, Condition and Quantity
    4. Click Add to Cart button
    5. Verify Info in cart page using Asserts in TestNG (Test pass if Both page items info equals to each other)
    6. Get SUBTOTAL -> Verify it with item price (use try because subtotal calculated with shipping fee)
    7. Get Shipping value and Print it

 */
public class InfoAndCart extends Navigate {

    private String itemName; //To get and store item Name from selection page
    private String itemPrice; //To get and store item Price from selection page
    private String itemCondition; //To get and store item Condition from selection page
    private String itemQuantity; //To get and store item Quantity from selection page

    @Test(priority = 12)
    public void getName(){
        //Locate Item Name by using Xpath and store it as a String
        itemName = driver1.findElement(By.xpath("//*[@id=\"LeftSummaryPanel\"]/div[1]/div[1]/div/h1")).getText();
        System.out.println(".......................... INFO.....................................\n");
        System.out.println("Item Name - "+ itemName);
    }
    @Test(priority = 13)
    public void getPrice(){
        //Locate Item Price by using Xpath and store it as a String
        itemPrice = driver1.findElement(By.xpath("//*[@itemprop=\"price\"]")).getText();
        System.out.println("Item Price - "+ itemPrice);
    }
    @Test(priority = 14)
    public void getCondition(){
        //Locate Item condition by using Xpath and store it as a String
        itemCondition = driver1.findElement(By.xpath("//*[@id=\"mainContent\"]/form/div[1]/div[1]/div/div[2]/div[1]/div/span/span[1]/span")).getText();
        //itemCondition = driver1.findElement(By.xpath("//span[@class='ux-textspans']")).getText(); // <- Not give expected result
        System.out.println("Item Condition - "+ itemCondition);//Print Condition
    }
    @Test(priority = 15)
    public void getQuantity(){
        // Locate the input element by its id
        WebElement qtyTextBox = driver1.findElement(By.id("qtyTextBox"));
        itemQuantity = qtyTextBox.getAttribute("value"); //Get the value from quantity box
        System.out.println("Item Quantity - "+ itemQuantity); //Print quantity
    }
    @Test(priority = 16)
    public void addToCart(){
        WebElement cartBtn = driver1.findElement(By.xpath("//*[@id=\"mainContent\"]/form/div[2]/div/div[1]/div[2]/ul/li[2]/div/a"));
        cartBtn.click(); // Click Add to cart button
    }

    //...................... Verify Items ..................................................
    @Test(priority = 17)
    public void checkName(){
        String title = driver1.findElement(By.className("item-title")).getText().trim();
        //System.out.println(title);
        Assert.assertEquals(title,itemName); //Verify Title of cart and Item name equals
    }
    @Test(priority = 18)
    public void checkPrice(){
        String totalPrice = driver1.findElement(By.cssSelector("div[class='item-price font-title-3']")).getText().trim();
       // System.out.println(totalPrice);
        Assert.assertEquals(totalPrice,itemPrice); // Verify both cart shown price and Item price is equals
    }
    @Test(priority = 19)
    public void checkQuantity(){
        String selectedQty ="";
        try {
            WebElement cartQuantity = driver1.findElement(By.cssSelector("select[data-test-id='qty-dropdown']"));
            selectedQty = cartQuantity.getAttribute("value");

        }catch (Exception e){
            // If there are only one item left it does not shows dropdown box for quantity
            System.out.println("There is only one item left or quantity Does Not Exist ");
            selectedQty ="1";
        }
        Assert.assertFalse(selectedQty.isEmpty(), "Quantity should not be empty."); //Make sure quantity is not empty
        Assert.assertEquals(selectedQty, itemQuantity); //Verify selected quantity is not changed

        System.out.println("Quantity(Cart) - "+selectedQty);
    }
    @Test(priority = 20)
    public void subTotal(){
        WebElement subTotal = driver1.findElement(By.cssSelector("div[data-test-id ='SUBTOTAL']"));
        String totalVal = subTotal.getText().trim();
        //System.out.println(totalVal);

        /*Used try-> because Subtotal calculate with shipping cost and shipping cost not always 0$
         Therefor subtotal may NOT be equal to Item price */
        try {
            Assert.assertEquals(totalVal, itemPrice);
        } finally {
            //Empty
        }
        ;
    }
    /*
    Get Shipping value using find element function
    Print it in Console
     */
    @Test(priority = 21)
    public void shippingCost(){
        // Some item doesn't consist shipping fee -> Because of that 'SHIPPING' does not exist for those
        try {
            WebElement shipping = driver1.findElement(By.cssSelector("div[data-test-id ='SHIPPING']"));
            String shippingValue = shipping.getText().trim();
            System.out.println("Shipping Value - "+shippingValue);
        }catch (Exception e){
            System.out.println("Shipping fee does not exist");
        }

    }
}
