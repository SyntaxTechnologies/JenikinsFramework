package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.PageInitializer;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class CommonMethods extends PageInitializer {

    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication(){
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);
        //cross browser testing
        switch (ConfigReader.getPropertyValue("browser")){
            case "chrome":
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
//                options.addArguments("start-maximized"); // open Browser in maximized mode
//                options.addArguments("disable-infobars"); // disabling infobars
//                options.addArguments("--disable-extensions"); // disabling extensions
//                options.addArguments("--disable-gpu"); // applicable to windows os only
//                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//                options.addArguments("--no-sandbox"); // Bypass OS security model
//
//                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
//                driver = new ChromeDriver(options);
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser name");
        }

        driver.manage().window().maximize();
        driver.get(ConfigReader.getPropertyValue("url"));
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //this method is used to initialize all the objects of the pages at the very beginning
        initializePageObjects();
    }

    public static void closeBrowser(){
        driver.quit();
    }

    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }
    //it will return 20 sec wait
    public static WebDriverWait getWait(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    //it will wait till the time element becomes clickable
    public static void waitForClickability(WebElement element){
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //to perform click operation

    public static void click(WebElement element){
    waitForClickability(element);
    element.click();
    }

    //select class for dropdown
    public static void selectDropdown(WebElement element, String text){
        Select s = new Select(element);
        s.selectByVisibleText(text);
    }

    //js click
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    //to perform click via javascript executor
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);
    }


    //to take the screenshot
    //cucumber accepts array of byte for screenshot
    public static byte[] takeScreenshot(String fileName){
        TakesScreenshot ts = (TakesScreenshot) driver;
       byte[] picBytes =  ts.getScreenshotAs(OutputType.BYTES);
       File sourceFile = ts.getScreenshotAs(OutputType.FILE);

       //how to name the screenshot

        try {
            FileUtils.copyFile(sourceFile, new File(Constants.SCREENSHOT_FILEPATH +
                    fileName + " " + getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }

    //to get the time in specific format so that we can add it in the name of screenshot
    public static String getTimeStamp(String pattern){
        Date date = new Date();
        //to format the date according to the choice of our own
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
