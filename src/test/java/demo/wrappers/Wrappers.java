package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    public static void wrapperSendKeys(WebElement element, String inputText) {
        try{
            element.sendKeys(inputText);
        }
        catch(Exception e){}
    }

    public static void wrapperClick(WebDriver driver, WebElement element) throws InterruptedException{

        element.click();
        Thread.sleep(3000);

    }





}
