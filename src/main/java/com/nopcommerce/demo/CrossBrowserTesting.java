package com.nopcommerce.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Scanner;

public class CrossBrowserTesting {
    protected static WebDriver driver;

    public static void main(String[] args) {
        //importing Scanner for entering users choice
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter \n1.IE\n2.Chrome\n3.Firefox" +"\n"+"Please Enter your choice");
        String webDriver = sc.nextLine();

        //Setting property for ie driver
        if (webDriver.equalsIgnoreCase("IE")) {
            //giving path for ie driver
            System.setProperty("webdriver.ie.driver", "src\\BrowserDriver\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            //opening website
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().fullscreen();
            //Closing website
            driver.quit();
        //Setting property for Chrome Driver
        } else if (webDriver.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "src\\BrowserDriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().fullscreen();
            driver.quit();

        } else if (webDriver.equalsIgnoreCase("Firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            //options.setBinary("C:\\soft\\FirefoxPortable\\FirefoxPortable.exe");

            System.setProperty("webdriver.gecko.driver", "src\\BrowserDriver\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.get("https://demo.nopcommerce.com/");
            driver.manage().window().fullscreen();
            driver.quit();

        } else {
            System.out.println("Invalid choice");
        }


    }
}

