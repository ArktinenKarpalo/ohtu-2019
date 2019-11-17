package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        /*
        sleep(2);

        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();

        sleep(3);
         */

        /*sleep(1);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep2");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);*/

       /* sleep(1);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys(Integer.toString(r.nextInt()));
        element = driver.findElement(By.name("password"));
        element.sendKeys("1234567");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1234567");
        element = driver.findElement(By.name("signup"));

        sleep(1);
        element.submit();

        sleep(1);*/

        /*sleep(1);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        Random r = new Random();

        element = driver.findElement(By.name("username"));
        element.sendKeys(Integer.toString(r.nextInt()));
        element = driver.findElement(By.name("password"));
        element.sendKeys("1234567");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("1234567");
        element = driver.findElement(By.name("signup"));

        sleep(1);
        element.submit();

        sleep(1);

        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(1);*/


        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000/2);
        } catch(Exception e){}
    }
}
