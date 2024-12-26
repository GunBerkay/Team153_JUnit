package tests.day06_assertions_testBaseKullanimi;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class C01_BagimliGorevler {

    static WebDriver  driver;

    // 3 farkli test method'u olusturup asagidaki gorevleri tamamlayin
    // 1- testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin
    // 2- phone icin arama yapip, arama sonucunda urun bulunabildigini test edin
    // 3- ilk urunu tiklayip, acilan sayfadaki urun isminde
    //    case sensitive olmadan "phone" bulundugunu test edin

    @BeforeAll
    public static void setup(){
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }

    @AfterAll
    public static void teardown(){
        driver.quit();
    }

    @Test
    public void test01(){
        // 1- testotomasyonu anasayfaya gidip, url'in testotomasyonu icerdigini test edin
        driver.get("https://www.testotomasyonu.com");
        String expectedUrlIcerik="testotomasyonu";
        String actualUrl=driver.getCurrentUrl();

        if (actualUrl.contains(expectedUrlIcerik)){
            System.out.println("Url testi PASSED");
        }else System.out.println("Url testi PASSED");
    }

    @Test
    public void test02(){
        // 2- phone icin arama yapip, arama sonucunda urun bulunabildigini test edin
        WebElement aramaKutusu= driver.findElement(By.id("global-search"));
        aramaKutusu.sendKeys("phone" + Keys.ENTER);
        List<WebElement> bulunanUrunElementleriList=driver.findElements(By.xpath("//*[@*='prod-img']"));

        int actualSonucSayisi=bulunanUrunElementleriList.size();
        if (actualSonucSayisi>0){
            System.out.println("Urun bulma testi PASSED");
        }else System.out.println("Urun bulma testi FAILED");
    }

    @Test
    public void test03(){
        // 3- ilk urunu tiklayip, acilan sayfadaki urun isminde
        driver.findElement(By.xpath("(//*[@*='prod-img'])[1]")).click();
        //    case sensitive olmadan "phone" bulundugunu test edin
        WebElement urunisimElementi= driver.findElement(By.xpath("//*[@class=' heading-sm mb-4']"));

        String expectedIsimIcerik="phone";
        String actualUrunIsmi=urunisimElementi.getText().toLowerCase();

        if (actualUrunIsmi.contains(expectedIsimIcerik)){
            System.out.println("Urun isim testi PASSED");
        }else System.out.println("Urun isim testi FAILED");

    }



}
