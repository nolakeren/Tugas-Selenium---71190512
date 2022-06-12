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

public class RegistrasiTest {
    String email;
    String password;
    String c_password;
    String firstname;
    ChromeDriver chromeDriver;
    @Given("browser dibuka")
    public void browser_dibuka() {
        System.out.println("Browser dibuka");
        System.setProperty(
                "webdriver.chrome.driver","src/main/resources/webdriver/chromedriver.exe");
        ///Objects.requireNonNull(getClass().getClassLoader().getResource("webdriver/chromedriver.exe")).getFile());
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
    }
    @Given("user ada di halaman register")
    public void user_ada_di_halaman_register() {
        System.out.println("Step 2 - User ada di halaman register");
        chromeDriver.navigate().to("https://demo.guru99.com/insurance/v1/register.php");
        List<WebElement> btnReset = chromeDriver.findElements(By.id("resetform"));
        List<WebElement> btnCreate = chromeDriver.findElements(By.name("submit"));
        if (btnCreate.size() > 0){
            System.out.println("Button create ditemukan");
        }
        if (btnReset.size() > 0){
            System.out.println("Button reset ditemukan");
        }
        Assertions.assertTrue(btnReset.size()>0,"Button reset found");
        Assertions.assertTrue(btnCreate.size()>0,"Button create found");
    }
    @When("^user memasukan ([^\"]*) ([^\"]*) ([^\"]*)$")
    public void user_memasukan_email_password(String namaDepan, String alamatEmail, String kataSandi) {
        System.out.println("User memasukan nama, email, password");
        firstname = namaDepan;
        password = kataSandi;
        c_password = kataSandi;
        email = alamatEmail;
        chromeDriver.findElement(By.name("firstname")).sendKeys(namaDepan);
        chromeDriver.findElement(By.name("email")).sendKeys(alamatEmail);
        chromeDriver.findElement(By.name("password")).sendKeys(kataSandi);
        chromeDriver.findElement(By.name("c_password")).sendKeys(kataSandi);
//        Assertions.assertTrue(!argFirstName.equalsIgnoreCase("") && argPassword.matches("[A-Za-z0-9]+") && argPassword.length()!=0 && argPassword.length() >= 8 && argPassword.length() <= 13 && !argEmail.equalsIgnoreCase(""),"Register gagal");
    }
    @When("tombol register diklik")
    public void tombol_register_diklik() {
        System.out.println("Tombol register ditekan");
        chromeDriver.findElement(By.name("submit")).click();
    }
    @Then("user diarahkan ke halaman login")
    public void user_diarahkan_ke_halaman_login() {
        List<WebElement> btnLogin = chromeDriver.findElements(By.xpath("/html/body/div[3]/form/div[3]/input"));
        System.out.println("User diarahkan ke halaman login");
        Assertions.assertTrue(!firstname.equalsIgnoreCase("") && password.matches("[A-Za-z0-9]+") && password.length()!=0 && password.length() >= 8 && password.length() <= 13 && !email.equalsIgnoreCase(""),"Register gagal");
        Assertions.assertTrue(btnLogin.size() > 0, "Button login tidak ditemukan");
        chromeDriver.close();
        chromeDriver.quit();
    }
}