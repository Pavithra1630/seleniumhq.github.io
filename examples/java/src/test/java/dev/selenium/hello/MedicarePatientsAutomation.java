
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class MedicarePatientsAutomation {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  
        WebDriver driver = new ChromeDriver(options);

        try {
           
            driver.get("https://fitpeo.com/medicare-calculator");

            
            WebElement slider = driver.findElement(By.id("sliderId")); 
            Actions move = new Actions(driver);
            move.dragAndDropBy(slider, 820, 0).perform(); 

            
            WebElement textField = driver.findElement(By.id("textFieldId")); 
            if (!textField.getAttribute("value").equals("820")) {
                System.err.println("Error: The text field value did not update to 820.");
            }

            
            textField.clear();
            textField.sendKeys("560");
            
            Thread.sleep(1000); 
            if (Integer.parseInt(slider.getAttribute("value")) != 560) {
                System.err.println("Error: The slider value did not update to 560.");
            }

           
            List<String> cptCodes = List.of("CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474");
            for (String cptCode : cptCodes) {
                WebElement checkbox = driver.findElement(By.id(cptCode)); 
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }

            
            WebElement reimbursementHeader = driver.findElement(By.id("reimbursementHeaderId")); 
            if (!reimbursementHeader.getText().equals("$110700")) {
                System.err.println("Error: The total recurring reimbursement value did not match.");
            }

            
            System.out.println("Automation tasks completed successfully.");

        } catch (NoSuchElementException e) {
            System.err.println("Error: Element not found - " + e.getMessage());
        } catch (WebDriverException e) {
            System.err.println("WebDriver error - " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred - " + e.getMessage());
        } finally {
            
            driver.quit();
        }
    }
}
