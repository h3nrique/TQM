package br.com.midiadev;

import br.com.midiadev.model.Request;
import br.com.midiadev.model.TQM;
import com.thoughtworks.xstream.XStream;
import org.apache.xpath.objects.XString;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        Object tqm = xstream.fromXML(new File("/home/h3nrique/git/TQM/config/passagens.xml"), TQM.class);
        System.out.println(tqm);
        String [] pesquisas = new String[] {
                "passagenspromo",
                "viajanet",
                "decolar",
                "cvc"};
        String [] urls = new String[] {
                "http://www.passagenspromo.com.br/site/aereo/pesquisa/Sao%20Paulo%20-%20SP,%20Brasil,%20Todos%20os%20aeroportos%20(SAO)/Fortaleza%20-%20CE,%20Brasil,%20Pinto%20Martins%20(FOR)/04-09-2015/07-09-2015/1/0/0",
                "http://passagens-aereas2.viajanet.com.br/s/voos-resultados#/SAO/FOR/RT/04-09-2015/07-09-2015/-/-/-/1/0/0/-/-",
                "http://www.decolar.com/shop/flights/results/roundtrip/SAO/FOR/2015-09-04/2015-09-07/1/0/0",
                "http://www.cvc.com.br/travel/resultado-passagens.aspx?searchtype=Air&Origem=SAO&Destino=FOR&Origem=FOR&Destino=SAO&Proximity=&ProximityId=0&Data=04/09/2015&RoundTrip=1&Data=07/09/2015&SomenteDireto=false&ExecutiveFlight=false&NumADT=1&NumCHD=0&NumINF=0&Hora=&Hora=&Multi=false"};
        String [] classes = new String[] {
                "selecao",
                "price",
                "fare-detail",
                "total"
        };

        for (int i = 0; i < pesquisas.length; i++) {
            String companhia = pesquisas[i];
            String url = urls[i];
            String className = classes[i];

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            FirefoxProfile profile = new FirefoxProfile();
            String file = String.format("/home/h3nrique/Desktop/%s-%s.pdf", simpleDateFormat.format(calendar.getTime()), companhia);
            profile.setPreference("print.always_print_silent", true);
            profile.setPreference("print.print_to_filename", file);
            profile.setPreference("print.print_paper_width", "415.0");

            WebDriver driver = new FirefoxDriver(profile);
            driver.manage().window().maximize();

            driver.get(url);

            waitForPageLoad(driver);

            waitForPageContent(driver, By.className(className));

            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("window.print();");

            Thread.sleep(10000);
            driver.quit();
            Thread.sleep(1000);
        }
    }

    public static void waitForPageLoad(final WebDriver webDriver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
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