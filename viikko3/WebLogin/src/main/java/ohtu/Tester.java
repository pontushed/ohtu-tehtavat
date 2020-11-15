package ohtu;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(2);

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        // epäonnistunut kirjautuminen: oikea käyttäjätunnus, väärä salasana
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);
        // nyt pitäisi löytyä "invalid username or password"
        element = driver.findElement(By.cssSelector("#error>p>em"));
        if (!element.getText().contains("invalid username or password")) {
            throw new IllegalStateException("Wrong output on invalid password");
        }

        // Skenaario: uuden käyttäjätunnuksen luominen
        driver.findElement(By.linkText("back to home")).click();
        driver.findElement(By.linkText("register new user")).click();

        element = driver.findElement(By.name("username"));
        Random r = new Random();
        element.sendKeys("pekko" + r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("okkep");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("okkep");
        sleep(1);
        driver.findElement(By.name("signup")).click();
        sleep(1);

        // Skenaario: uuden käyttäjätunnuksen luomisen jälkeen tapahtuva
        // ulkoskirjautuminen sovelluksesta
        driver.findElement(By.linkText("continue to application mainpage")).click();
        sleep(1);
        driver.findElement(By.linkText(("logout"))).click();
        sleep(2);
        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
