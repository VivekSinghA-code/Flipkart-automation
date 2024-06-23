package demo.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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