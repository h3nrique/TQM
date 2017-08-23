package br.com.midiadev;

import br.com.midiadev.model.FieldType;
import br.com.midiadev.model.Requests;
import br.com.midiadev.model.TQM;
import br.com.midiadev.model.WaitField;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by h3nrique on 8/12/15.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        XStream xstream = new XStream();
        xstream.processAnnotations(TQM.class);
        xstream.autodetectAnnotations(true);
        TQM tqm = (TQM) xstream.fromXML(new File(Test.class.getResource("/passagens.xml").getFile()));

        checkDirs(tqm);

        for (Requests.Request request : tqm.getRequests().getRequest()) {
            String companhia = request.getName();
            String url = request.getUrl();
            WaitField requestWaitField = request.getWaitField();

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            FirefoxProfile ffProfile = new FirefoxProfile();
            String file = String.format(tqm.getPrintDir().concat("pageshot-%s-%s.pdf"), simpleDateFormat.format(calendar.getTime()), companhia);
            ffProfile.setPreference("print.always_print_silent", true);
            ffProfile.setPreference("print.print_to_filename", file);
            ffProfile.setPreference("print.print_paper_width", "415.0");

            System.setProperty("webdriver.gecko.driver", tqm.getWebdriverGeckoDriver());
            File pathToBinary = new File(tqm.getFirefoxBin());
            FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(ffBinary);
            firefoxOptions.setProfile(ffProfile);

            WebDriver driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();

            driver.get(url);

            waitForPageLoad(driver, request.getTimeoutLoadPageInSeconds());

            switch (requestWaitField.getType()) {
                case ID:
                    waitForPageContent(driver, By.id(requestWaitField.getName()));
                    break;
                case NAME:
                    waitForPageContent(driver, By.name(requestWaitField.getName()));
                    break;
                case CLASS:
                    waitForPageContent(driver, By.className(requestWaitField.getName()));
                    break;

            }

            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("window.print();");

            Thread.sleep(10000);
            driver.quit();
            Thread.sleep(1000);
        }
    }

    private static void checkDirs(TQM tqm) {
        if(!new File(tqm.getLogDir()).exists()){
            new File(tqm.getLogDir()).mkdir();
        }
        if(!new File(tqm.getPrintDir()).exists()){
            new File(tqm.getPrintDir()).mkdir();
        }
    }

    public static void waitForPageLoad(final WebDriver webDriver, Integer timeOutInSeconds) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(pageLoadCondition);
    }

    public static void waitForPageContent(final WebDriver webDriver, final By byField) {
        ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    try {
                        WebElement webElement = driver.findElement(byField);
                        return webElement != null && webElement.isDisplayed();
                    } catch (Exception err) {
                        return false;
                    }
                }
            };
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(pageLoadCondition);
    }
}
