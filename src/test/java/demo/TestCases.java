package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.time.Duration;



// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow testCase01 testCase02... format or what is provided in
     * instructions
     */

    // testCase01: Go to www.flipkart.com. Search "Washing Machine". Sort by
    // popularity and print the count of items with rating less than or equal to 4
    // stars.

    @SuppressWarnings("deprecation")
    @Test(enabled = true, alwaysRun = true)
    public static void testCase01() throws InterruptedException {
        System.out.println("Start the testcase01");
        driver.get("https://www.flipkart.com");

        WebElement searchElement = driver
                .findElement(By.xpath("//input[@title ='Search for Products, Brands and More']"));
        // searchElement.sendKeys("Washing Machine");

        Wait<WebDriver> webdwait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

        webdwait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@title ='Search for Products, Brands and More']")));

        Wrappers.wrapperSendKeys(searchElement, "Washing Machine");
        Thread.sleep(5000);


        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        action.sendKeys(searchElement, Keys.ENTER).perform();

        WebElement popularityElement = driver.findElement(By.xpath("//div[text() ='Popularity']")); //// div[@class='sHCOk2']//div[2]
        // popularityElement.click();

        Wait<WebDriver> webdwait = new WebDriverWait(driver, Duration.ofSeconds(10));   // WebDriverWait(driver, Duration.ofSeconds(10));

        webdwait.until(ExpectedConditions.elementToBeClickable(popularityElement));

        Wrappers.wrapperClick(driver, popularityElement);
        Thread.sleep(3000);
        


        List<WebElement> ratingElements = driver.findElements(By.xpath("//div[@class ='XQDdHH']"));

        int count = 0;
        for (WebElement productElement : ratingElements) {

            String productText = productElement.getText();

            // System.out.println(productText);

            List<Double> productList = new ArrayList<>();

            productList.add(Double.parseDouble(productText.replaceAll("[^0-9\\.]", "")));

            for (Double ratingOfStar : productList) {

                if (ratingOfStar <= 4) {
                    count++;
                }
            }

        }
        System.out.println("final count of 4 or less stars isequals to  " + count);

    }

    @Test(enabled = true, alwaysRun = true)
    public static void testCase02() throws InterruptedException {

        System.out.println("Start the testCase02");

        driver.get("https://www.flipkart.com");

        WebElement searchElement = driver
                .findElement(By.xpath("//input[@title ='Search for Products, Brands and More']"));
        // searchElement.sendKeys("iPhone");

        Wrappers.wrapperSendKeys(searchElement, "iPhone");
        Thread.sleep(5000);

        Actions action = new Actions(driver);
        action.sendKeys(searchElement, Keys.ENTER).perform();
        Thread.sleep(5000);

        // Find all products
        List<WebElement> productElements = driver.findElements(By.xpath("//div[@class='tUxRFH']"));


        // Create a HashMap to store title and discount
        Map<String, String> titleDiscountMap = new HashMap<>();

        // Iterate through each product element and extract title and discount
        for (WebElement productElement : productElements) {

            // Extract title
            WebElement titleElement = productElement
                    .findElement(By.xpath(".//div[@class='col col-7-12']//div[@class='KzDlHZ']"));

            String title = titleElement.getText().trim();

            // Extract discount if available

            WebElement discountElement = productElement.findElement(By.xpath(".//div[@class='UkUFwK']//span"));
            String discountText = discountElement.getText().trim();

            // Extract numerical discount value (assuming percentage format)
            String discountValueStr = discountText.replaceAll("[^0-9]", ""); // Remove non-numeric characters
            int discountValue = Integer.parseInt(discountValueStr); // Parse the numeric value

            // Store title and discount in HashMap if discount > 17%
            if (discountValue > 17) {
                titleDiscountMap.put(title, discountText);
            }

        }

        // Print titles of products with discount > 17%
        for (Map.Entry<String, String> entry : titleDiscountMap.entrySet()) {
            System.out.println("Title: " + entry.getKey());
            // Discount can also be printed if needed:
            System.out.println("Discount: " + entry.getValue());
            System.out.println("------------------------");

        }



    }

    // Class to hold Product details (Title, Image URL, Reviews)
    static class ProductDetails {
        private String title;
        private String imageUrl;
        private int reviews;

        public ProductDetails(String title, String imageUrl, int reviews) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.reviews = reviews;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getReviews() {
            return reviews;
        }
    }
    @Test(enabled = true, alwaysRun = true)
    public static void testCase03() throws InterruptedException {

        System.out.println("Start the testCase03");

        driver.get("https://www.flipkart.com");

        WebElement searchElement = driver
                .findElement(By.xpath("//input[@title ='Search for Products, Brands and More']"));
        // searchElement.sendKeys("Coffee Mug");
        Wrappers.wrapperSendKeys(searchElement, "Coffee Mug");
       
        Thread.sleep(5000);

        Actions action = new Actions(driver);
        action.sendKeys(searchElement, Keys.ENTER).perform();
        Thread.sleep(5000);

        WebElement fourStarcheckboxElement = driver
                .findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));

                Thread.sleep(3000);

        fourStarcheckboxElement.click();

        Thread.sleep(3000);

        List<WebElement> coffeeMugsElement = driver.findElements(By.xpath("//div[@class='slAVV4']"));

        // Wait<WebDriver> wait = new WebDriverWait(driver, null);

        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slAVV4']")));

        // Create a list to store product details
        List<ProductDetails> products = new ArrayList<>();

        // Iterate through each product element and extract details (Title, Image URL,
        // Reviews)
        for (WebElement productElement : coffeeMugsElement) {

            // Extract title
            WebElement titleElement = productElement.findElement(By.xpath(".//a[@class='wjcEIp']"));
            Thread.sleep(3000);
            String title = titleElement.getText().trim();

            // Extract image URL (you may need to adjust the XPath for actual
            // implementation)
            WebElement imageElement = productElement.findElement(By.xpath(".//img[@class='DByuf4']"));
            Thread.sleep(2000);
            String imageUrl = imageElement.getAttribute("src");

            // Extract number of reviews
            WebElement reviewElement = productElement
                    .findElement(By.xpath(".//span[@class='Wphh3N']"));
            String reviewText = reviewElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
            int reviews = Integer.parseInt(reviewText);

            // Create ProductDetails object and add to the list
            ProductDetails product = new ProductDetails(title, imageUrl, reviews);
            products.add(product);

            // Sort products based on number of reviews (descending order)
            Collections.sort(products, (p1, p2) -> p2.getReviews() - p1.getReviews());


            // Print the Title and Image URL of the top 5 products with highest number of
            // reviews
            int count = 0;
            for (ProductDetails product1 : products) {
                if (count<5) {
                    System.out.println("Title: " + product1.getTitle());
                    System.out.println("Image URL: " + product1.getImageUrl());
                    System.out.println("------------------------");
                    count++;
                } else {
                    break;
                }
            }
        }

    }

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest(enabled = true, alwaysRun = true)
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest(enabled = true, alwaysRun = true)
    public void endTest() {
        // driver.close();
        driver.quit();

    }
}