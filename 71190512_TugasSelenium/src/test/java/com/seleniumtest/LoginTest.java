package com.seleniumtest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class LoginTest {
    String email;
    String password;
    ChromeDriver chromeDriver;
    @Given("browser open")
    public void browser_open() {
        System.out.println(" Browser dibuka");
        System.setProperty(
                "webdriver.chrome.driver","src/main/resources/webdriver/chromedriver.exe");
        ///Objects.requireNonNull(getClass().getClassLoader().getResource("webdriver/chromedriver.exe")).getFile());
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40)); // menunggu browser terbuka
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("user berada di halaman login")
    public void user_berada_di_halaman_login() {
        System.out.println("User berada di halaman login");
        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/index.php");
    }
    @When("^user memasukan ([^\"]*) and ([^\"]*)$")
    public void user_memasukan_email_dan_password(String alamatEmail, String kataSandi) {
        System.out.println("User memasukan email dan password");
        email = alamatEmail;
        password = kataSandi;
        chromeDriver.findElement(By.name("email")).sendKeys(alamatEmail);
        chromeDriver.findElement(By.name("password")).sendKeys(kataSandi);
    }
    @When("tombol login ditekan")
    public void tombol_login_ditekan() {
        System.out.println("Tombol login ditekan");
        chromeDriver.findElement(By.name("submit")).click();
    }
    @Then("user redirect ke home page")
    public void user_redirect_to_home_page() {
        System.out.println("User diarahkan ke halaman utama");
        Assertions.assertTrue(password.matches("[A-Za-z0-9]+") && password.length()!=0 && password.length() >= 8 && password.length() <= 13 && !email.equalsIgnoreCase(""),"Login gagal");
        List<WebElement> list = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/input"));
        Assertions.assertTrue(list.size()>0,"Tombol logout tidak ditemukan");
        chromeDriver.close();
        chromeDriver.quit();
    }
}
