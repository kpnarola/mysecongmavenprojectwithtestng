package com.nopcommerce.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ToVerifyAddminUserCanAddEmployeeSuccessfully {


        protected static WebDriver driver;

        @BeforeMethod
        public void openBrowser() {
            System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().fullscreen();
            driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
        }

//    @AfterMethod
//    public void closeBrowser() {
//        driver.quit();
//    }

        //Reusable method for click
        public void clickOnElement(By by) {
            driver.findElement(by).click();
        }

        //Reusable method for sendKeys
        public void enterText(By by, String text) {
            driver.findElement(by).sendKeys(text);
        }

        @Test
        public void toVerifyAdminUserShouldBeAbleToCreateEmployeeAccountWithLoginCredential(){
            enterText(By.id("txtUsername"),"Admin");
            enterText(By.id("txtPassword"),"admin123");
            clickOnElement(By.id("btnLogin"));
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("menu_pim_viewPimModule")));
            clickOnElement(By.id("menu_pim_viewPimModule"));

            Actions actions = new Actions(driver);
            WebElement pimInMenu = driver.findElement(By.id("menu_pim_viewPimModule"));
            actions.moveToElement(pimInMenu);
            WebElement addEmployeeMenu = driver.findElement(By.id("menu_pim_addEmployee"));
            actions.moveToElement(addEmployeeMenu);
            actions.click().build().perform();
            enterText(By.id("firstName"),"Jay");
            enterText(By.id("lastName"),"Zaman");
            clickOnElement(By.id("chkLogin"));
            clickOnElement(By.id("btnSave"));
            enterText(By.id("user_name"),"JayZaman");
            enterText(By.id("user_password"),"Newham1!");
            enterText(By.id("re_password"),"Newham1!");
            clickOnElement(By.id("btnSave"));

        }





    }


