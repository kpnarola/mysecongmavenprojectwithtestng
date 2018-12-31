package com.nopcommerce.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserShouldBeAbleToRegisterSuccessfully {
    protected static WebDriver driver;


    @BeforeMethod
    public void openBrowser() {

        //Setting Chrome Driver
        System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");

        //Creating object for ChromeDriver
        driver = new ChromeDriver();

        // ImplicityWait for Driver
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        //Maximising Browser
        driver.manage().window().fullscreen();

        //Opening Url
        driver.get("https://demo.nopcommerce.com/");
    }

    @AfterMethod
     //closing Browser
    public void closeBrowser() {
        driver.quit();
    }

    //Reusable method for click
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    //Reusable method for SendKey
    public void enterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //Reusable method for Registration
    public void registration() {

        //Click on Register Button
        clickOnElement(By.linkText("Register"));

        //Click on gender
        clickOnElement(By.id("gender-male"));

        //Click on First Name
        enterText(By.id("FirstName"), "Kp");

        //Click on Last Name
        enterText(By.id("LastName"), "patel");

        //Date of Birth Field

        //Input Date of Birth Day by using Value
        Select dateOfBirthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dateOfBirthDay.selectByValue("6");

        //Input Date of Birth Month by using Index
        Select dateOfBirthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        dateOfBirthMonth.selectByIndex(4);

        //Input Date of Birth Year
        Select dateOfBirthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        dateOfBirthYear.selectByVisibleText("1979");

        //For Enter email Id

        //for Re enter Email id creating date format
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyyHHmmSS");
        Date date = new Date();
        String date1 = dateFormat.format(date);

        //Entering email Address with Date Format

        //Variable Declaration for email Id
        String emailAddress = "kppatel+" + date1 + "@yahoo.com";

        //Entering email id in the email field
        driver.findElement(By.id("Email")).sendKeys(emailAddress);

        //For Company Detail
        //Enter Company name
        enterText(By.id("Company"), "Yogiraj");

        //For Options - Click on Newsletters box
        clickOnElement(By.id("Newsletter"));

        //For Your Password Field - password for registration

        //Declaring variable for password
        String password = "test123";

        //Entering Password in Password field
        enterText(By.id("Password"), password);

        //Confirming Password
        enterText(By.id("ConfirmPassword"), password);

        //Click on Registration Button
        clickOnElement(By.id("register-button"));
    }

    @Test(priority = 0)
    public void userShouldBeAbleToRegisterSuccessfully() {

        //registering an account using reusable registration method
        registration();

        //Declaring variable for expected Result
        String expectedRegisterSuccessMessage = "Your registration completed";

        //Declaring variable for actual result and getting actual result from the registration completed successful page
        String actualRegisterSuccessMessage = driver.findElement(By.xpath("//div[contains(text(),'Your registration completed')]")).getText();

        //Verifying for an account registration successfully
        Assert.assertEquals(actualRegisterSuccessMessage, expectedRegisterSuccessMessage, "Sorry your registration is not successful - It");

    }

    @Test(priority = 1)
    public void registeredUserShouldBeAbleToSendEmailWithProductSuccessfully() {
         /* /*https://demo.nopcommerce.com/
      click on Apple MacBook Pro 13-inch - email a friend -fill up required details and click on Send Email button
      Registered user should be able to send Email with product Successfully*/

        //registering an account using reusable registration method
        registration();
        //click on continue button
        clickOnElement(By.name("register-continue"));

        //Selecting Product Apple MavBook Pro 13-inch
        clickOnElement(By.linkText("Apple MacBook Pro 13-inch"));

        //Clicking on Email a Friend button to send Product detail with Email
        clickOnElement(By.xpath("//input[@value='Email a friend']"));

        //Entering Friends Email Address
        enterText(By.id("FriendEmail"), "pkpatel@yahoo.com");

        //Entering Personal Message
        enterText(By.id("PersonalMessage"), "It is very nice product have a look");

        //Clicking on send email button
        clickOnElement(By.name("send-email"));

        //Declaring variable for expected result
        String expectedSendEmailSuccessfullyMessage = "Your message has been sent.";

        //Declaring variable for actual result
        String actualRegisterSuccessMessage = driver.findElement(By.xpath("//div[@class= 'result']")).getText();
        //Verifying only registered customer can send email successfully
        Assert.assertEquals(actualRegisterSuccessMessage, expectedSendEmailSuccessfullyMessage, "Sorry You are not able to send email successfully");

    }

    @Test(priority = 5)
//To verify only registered user can use Email a friend feature:
    public void toVerifyUnRegisteredUserShouldNotBeAbleToSendEmail() {

        //selecting Computer Field from the website
        clickOnElement(By.linkText("Computers"));

        //Selecting Desktop form category list of computer
        clickOnElement(By.linkText("Desktops"));

        //Selecting Product
        clickOnElement(By.linkText("Lenovo IdeaCentre 600 All-in-One PC"));

        //Clicking on email a friend button from the selected product
        clickOnElement(By.xpath("//input[@value='Email a friend']"));

        //Entering friend's email address
        enterText(By.id("FriendEmail"), "pnpatel@gmail.com");

        //Entering my email address
        enterText(By.id("YourEmailAddress"), "skpatel@hotmail.com");

        //Entering personal message
        enterText(By.id("PersonalMessage"), "I wanted to show you this nice product");

        //Clicking on send email button
        clickOnElement(By.name("send-email"));

        //Declaring variable for expected result
        String expectedErrorMessage = "Only registered customers can use email a friend feature";

        //Declaring variable for Actual result and
        String actualErrorMessage = driver.findElement(By.xpath("//li[contains(text(),'Only registered customers can use email a friend feature')]")).getText();

        //Verifying only registered customer can send email successfully
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Display Message is not as per expected, test is failed");
    }

    @Test(priority = 6)
    public void toVerifyUserMustHaveToAcceptTermsAndConditionForCheckOut() {
        //Registering email account by using reusable method of registration
        registration();

        //click on continue button for continuing other page for selecting product
        clickOnElement(By.name("register-continue"));

        //Selecting Product
        clickOnElement(By.linkText("Build your own computer"));

        //Selecting Hdd button in product Description
        clickOnElement(By.id("product_attribute_3_6"));

        //Clicking on add to cart button
        clickOnElement(By.id("add-to-cart-button-1"));

        //Clicking on shopping cart button
        clickOnElement(By.xpath("//span[@class='cart-label']"));

        //Clicking on Checkout button
        clickOnElement(By.id("checkout"));

        //Declaring variable for expected result
        String expectedErrorMessage = "Please accept the terms of service before the next step.";

        //Declaring variable for actual result and getting actual result from the page
        String actualErrorMessage = driver.findElement(By.xpath("//p[contains(text(),'Please accept the terms of service before the next step.')]")).getText();

        //Verifying for buying any product successfully user must have to accept terms and condition
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Display message is not as per expected, Test failed");
    }

    @Test(priority = 2)
    public void toVerifyRegisteredUserShouldBeAbleToBuyAnySingleProductSuccessfully() {
        //Registering account by using re usable method for Registration
        registration();

        //click on continue button
        clickOnElement(By.name("register-continue"));

        //Selecting Product
        clickOnElement(By.linkText("HTC One M8 Android L 5.0 Lollipop"));

        //Clicking on add to cart button
        clickOnElement(By.id("add-to-cart-button-18"));

        //Clicking on shopping cart button
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));

        //Clicking on terms of service
        clickOnElement(By.id("termsofservice"));

        //Clicking on Checkout button
        clickOnElement(By.id("checkout"));

        //Selecting country for billing adress
        Select country = new Select(driver.findElement(By.id("BillingNewAddress_CountryId")));
        country.selectByVisibleText("United Kingdom");

        //Entering city name
        enterText(By.id("BillingNewAddress_City"), "London");

        //Entering first line of message
        enterText(By.id("BillingNewAddress_Address1"), "15,Talbet Road");

        //Entering Second line of message
        enterText(By.id("BillingNewAddress_Address2"), "Wembley");

        //Entering postal code
        enterText(By.id("BillingNewAddress_ZipPostalCode"), "HA04UA");

        //Entering phone number
        enterText(By.id("BillingNewAddress_PhoneNumber"), "07894234443");

        //Entering Fax number
        enterText(By.id("BillingNewAddress_FaxNumber"), "02073638352");

        //Clicking on continue button for shipping method
        clickOnElement(By.xpath("//input[@onclick='Billing.save()']"));

        //Step - 3 Shipping Method

        //Clicking on first option Ground from Shipping method
        clickOnElement(By.id("shippingoption_0"));

        //Clicking on continue button for Payment Method
        clickOnElement(By.xpath("//input[@onclick='ShippingMethod.save()']"));

        //Step-4 Payment method

        //Click on Credit Card option
        clickOnElement(By.id("paymentmethod_1"));

        //Click on continue button for payment information
        clickOnElement(By.xpath("//input[@onclick='PaymentMethod.save()']"));

        //Step 5- Payment Information

        //Entering card holder name
        enterText(By.id("CardholderName"), "Shital");

        //Entering Card number
        enterText(By.id("CardNumber"), "4111 1111 1111 1111");

        //Entering Expire month for Credit card
        Select expireDate = new Select(driver.findElement(By.id("ExpireMonth")));
        expireDate.selectByValue("2");

        //Entering Expire Year
        Select expireYear = new Select(driver.findElement(By.id("ExpireYear")));
        expireYear.selectByVisibleText("2020");

        //Entering short code
        enterText(By.id("CardCode"), "737");

        //Clicking on Continue button for order confirmation
        clickOnElement(By.xpath("//input[@onclick=\"PaymentInfo.save()\"]"));

        //Step 6-Confirm Order

        //Clicking on Confirm Order button
        clickOnElement(By.xpath("//input[@onclick=\"ConfirmOrder.save()\"]"));

        //Declaring String for expected result
        String expectedResult = "Your order has been successfully processed!";

        //declaring string for actual result
        String actualResult = driver.findElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]")).getText();

        //Verifying  Registered user can buy any single product successfully
        Assert.assertEquals(expectedResult, actualResult, "You are not able to buy product successfully, test failed");
    }

    @Test(priority = 4)
    public void toVerifyUserShouldBeAbleToSortByPriceHighToLow() {
        //Clicking on Apparel
        clickOnElement(By.linkText("Apparel"));

        //Clicking on Clothing
        clickOnElement(By.xpath("//img[@alt='Picture for category Clothing']"));

        //Selecting Product
        Select sortByHighToLow = new Select(driver.findElement(By.id("products-orderby")));

        //Sort by Product price High to Low
        sortByHighToLow.selectByVisibleText("Price: High to Low");

        //Taking all Product Price for List by using Xpath
        List<WebElement> price = driver.findElements(By.xpath("//div/div[2]/div[3]/div[1]/span"));

        //Converting Xpath List in ArrayList for getting price
        List<String> prices = new ArrayList<>();

        //adding prices in array list from webElement
        for (WebElement e : price) {
            prices.add(e.getText());
        }
        //Getting String Price for First Product
        String firstIndexPrice = prices.get(0);

        //Removing Character and Special Character from String for converting in Float & Result Comparision
        String firstIndexDigitsOnly = firstIndexPrice.replaceAll("[^0-9.]|$|Ђ +", "");

        //Converting String in Float For Comparing Assert
        Float firstProductPrice = Float.parseFloat(firstIndexDigitsOnly);

        //Getting String Price for last product
        String lastIndexPrice = prices.get(prices.size() - 1);

        //Removing Character and Special Character from String for converting in Float & Result Comparision
        String lastIndexDigitOnly = lastIndexPrice.replaceAll("[^0-9.]| $ | Ђ +", "");

        //Converting String in Float For Comparing Assert
        Float lastProductPrice = Float.parseFloat(lastIndexDigitOnly);

        //Verifying user should able to sort price high to low
        Assert.assertTrue(firstProductPrice > lastProductPrice, "Sorting high to low prices is not working ");

    }


    //Sorting prices low to high by using *collection sorted method
    @Test(priority = 5)
    public void toVerifyUserShouldBePricesSortingLowToHigh() {
        //Clicking on Apparel
        clickOnElement(By.linkText("Apparel"));

        //Clicking on Clothing
        clickOnElement(By.xpath("//img[@alt='Picture for category Clothing']"));

        //Selecting Product From Index and filtering the price High to Low
        Select sortByHighToLow = new Select(driver.findElement(By.id("products-orderby")));
        sortByHighToLow.selectByVisibleText("Price: Low to High");

        //Getting Prices for List by using X path
        List<WebElement> price = driver.findElements(By.xpath("//div/div[2]/div[3]/div[1]/span"));

        //Converting List Price in Array
        List<String> prices = new ArrayList<String>();

        //adding prices in array list from webElement
        for (WebElement e : price) {
            prices.add(e.getText());
        }

        //Making List for Sorted price
        List<String> sortedPrices = new ArrayList<>(prices);

        //Reversing Price for result Comparision
        // Collections.reverse(price);
        Collections.sort(prices);

        //comparing both Prices
        System.out.println(sortedPrices.equals(prices));


        //verifying the prices are sort high to low
        Assert.assertEquals(prices, sortedPrices, "Sorting Prices - low to high is not working");

    }


}











